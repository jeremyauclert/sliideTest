package com.test.news.tests;

import android.os.Bundle;

import androidx.annotation.NonNull;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberAndroidJUnitRunner;
import cucumber.runtime.android.CucumberAndroidJUnitArguments;

@CucumberOptions(glue = {"com.test.news.tests"}, features = {"features"}, tags = {"@login, @news"})
public class NewsAppAndroidTestRunner extends CucumberAndroidJUnitRunner {

    private CucumberAndroidJUnitArguments cucumberJUnitRunnerCore;

    @Override
    public void onCreate(final Bundle bundle) {
        cucumberJUnitRunnerCore = new CucumberAndroidJUnitArguments(bundle);
        super.onCreate(cucumberJUnitRunnerCore.processArgs());
    }

    @NonNull
    @Override
    public CucumberAndroidJUnitArguments getArguments() {
        return cucumberJUnitRunnerCore;
    }

}
