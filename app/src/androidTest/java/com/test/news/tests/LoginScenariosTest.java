package com.test.news.tests;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.test.news.features.login.presentation.LoginActivity;
import com.test.news.R;
import com.test.news.util.WaitUtil;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class LoginScenariosTest {

    @Rule
    private ActivityTestRule<LoginActivity> activityTestRule  = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity activity;

    @After
    public void tearDown() {
        activityTestRule.getActivity().finish();
    }

    @Given("^I have a LoginActivity")
    public void I_have_a_LoginActivity(){
        activityTestRule.launchActivity(new Intent());
        activity = activityTestRule.getActivity();
    }




    @When("^I input email (\\S+)$")
    public void I_input_email(final String username) {
        onView(withId(R.id.textViewLogo)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextUserName)).perform(typeText(username));
    }

    @And("^I input password (\\S+)$")
    public void I_input_password(final String password) {
        onView(withId(R.id.editTextPassword)).perform(typeText(password));
        closeSoftKeyboard();
    }

    @And("I press submit button")
    public void I_press_submit_button() {

        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @Then("^I should (true|false) auth error$")
    public void I_should_see_error_on_the_editTextView(final boolean viewName) throws InterruptedException {
        if (!viewName)    {
            Thread.sleep(1000);
            onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
            Thread.sleep(5000);
            onView(withId(R.id.recyclerViewNews)).check(matches(isDisplayed()));
        } else {
            Thread.sleep(5000);
            onView(withId(R.id.editTextUserName)).perform(click());
            Thread.sleep(5000);
            onView(withText(containsString("Wrong user name")))
                    .inRoot(RootMatchers.isPlatformPopup())
                    .check(matches(isDisplayed()));


        }
    }


}
