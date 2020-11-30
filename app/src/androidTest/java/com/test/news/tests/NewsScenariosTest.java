package com.test.news.tests;

import android.content.Intent;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import com.google.android.material.textfield.TextInputLayout;
import com.test.news.R;
import com.test.news.features.login.presentation.LoginActivity;
import com.test.news.features.news.presentation.NewsActivity;
import com.test.news.util.WaitUtil;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class NewsScenariosTest {

    @Rule
    private ActivityTestRule<NewsActivity> activityTestRule  = new ActivityTestRule<NewsActivity>(NewsActivity.class);

    private NewsActivity newsActivity;

    private UiDevice mDevice;

    @After
    public void tearDown() {
        activityTestRule.getActivity().finish();
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi enable");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data enable");

    }

    @Before
    public void before() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        assertThat(mDevice, notNullValue());
    }

    @Given("^there is internet connection$")
    public void thereis_internet_connection() {
        activityTestRule.launchActivity(new Intent());
        newsActivity = activityTestRule.getActivity();
    }
    @Then("^images are displayed in the rows on the list$")
    public void images_are_displayed_in_the_rows_on_the_list()  {
        try {
            Thread.sleep(10000);
            onView(withId(R.id.recyclerViewNews)).check(matches(isDisplayed()));

            onView(withId(R.id.recyclerViewNews))
                    .perform(actionOnItemAtPosition(0, click()));
            Thread.sleep(2000);
            getBackToApp("News");
            onView(withId(R.id.recyclerViewNews))
                    .perform(actionOnItemAtPosition(1, click()));
            Thread.sleep(2000);
            getBackToApp("News");
            Thread.sleep(2000);
            onView(withId(R.id.recyclerViewNews))
                    .perform(actionOnItemAtPosition(2, click()));
            getBackToApp("News");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^there is no internet connection$")
    public void thereis_no_internet_connection() {

        try {
            activityTestRule.launchActivity(new Intent());
            newsActivity = activityTestRule.getActivity();
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi disable");
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data disable");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^Failed to load news$")
    public void error_message_is_displayed_and_Retry_button() throws InterruptedException {
        Thread.sleep(5000);
        onView(allOf(withId(R.id.textViewError))).check(matches(withText("Failed to load news")));
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi enable");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data enable");
    }

    private void getBackToApp(String appDescription){
        try {
            mDevice.pressRecentApps();
            UiSelector selector = new UiSelector();
            UiObject recentApp = mDevice.findObject(selector.descriptionContains(appDescription));
            recentApp.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}


