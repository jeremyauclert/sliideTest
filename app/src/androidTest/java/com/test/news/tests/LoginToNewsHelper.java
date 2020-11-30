package com.test.news.tests;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.news.R;
import com.test.news.features.login.presentation.LoginActivity;

import org.junit.After;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginToNewsHelper {

    @Rule
    private ActivityTestRule<LoginActivity> activityTestRule  = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity activity;

    @After
    public void tearDown() {
        activityTestRule.getActivity().finish();
    }

    @Given("I have a LoginScreen")
    public void I_have_a_LoginActivity(){
        activityTestRule.launchActivity(new Intent());
        activity = activityTestRule.getActivity();
    }

    @When("^I submit (\\S+) and (\\S+)")
    public void I_input_username_and_password(final String username, final String password) {
        onView(withId(com.test.news.R.id.textViewLogo)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextUserName)).perform(typeText(username));
        onView(withId(R.id.editTextPassword)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @Then("I should be logged in")
    public void I_should_be_logged_in() throws InterruptedException {
    }
}
