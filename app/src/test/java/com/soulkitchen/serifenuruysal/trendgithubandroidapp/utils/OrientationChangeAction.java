/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 - Nathan Barraille
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.soulkitchen.serifenuruysal.trendgithubandroidapp.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Matcher;

import java.util.Collection;

import androidx.appcompat.app.AppCompatActivity;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
/**
 * Created by S.Nur Uysal on 6.12.2018.
 */
public class OrientationChangeAction implements ViewAction {
    private final int orientation;

    private OrientationChangeAction(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public Matcher<View> getConstraints() {
        return isRoot();
    }

    @Override
    public String getDescription() {
        return "change orientation to " + orientation;
    }

    @Override
    public void perform(UiController uiController, View view) {
        uiController.loopMainThreadUntilIdle();
        AppCompatActivity activity = getActivity(view);
        activity.setRequestedOrientation(orientation);

        Collection<AppCompatActivity> resumedActivities =
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
        if (resumedActivities.isEmpty()) {
            throw new RuntimeException("Could not change orientation");
        }
    }

    public AppCompatActivity getActivity(View view) {
        AppCompatActivity activity = null;
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof AppCompatActivity) {
                activity = (AppCompatActivity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        if (activity == null && view instanceof ViewGroup) {
            ViewGroup v = (ViewGroup) view;
            int c = v.getChildCount();
            for (int i = 0; i < c && activity == null; ++i) {
                activity = getActivity(v.getChildAt(i));
            }
        }

        return activity;
    }

    public static ViewAction orientationLandscape() {
        return new OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static ViewAction orientationPortrait() {
        return new OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}