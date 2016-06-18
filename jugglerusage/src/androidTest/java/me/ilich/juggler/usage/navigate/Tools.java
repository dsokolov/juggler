package me.ilich.juggler.usage.navigate;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import me.ilich.juggler.usage.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static me.ilich.juggler.usage.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by ilich on 17.06.16.
 */
public class Tools {

    public static void check(boolean hasContent, boolean hasToolbar, boolean hasNavigation) {
        if (hasContent) {
            onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        } else {
            onView(withText(R.string.stub_text_content)).check(doesNotExist());
        }
        if (hasToolbar) {
            onView(withText(R.string.stub_text_title)).check(matches(isDisplayed()));
        } else {
            onView(withText(R.string.stub_text_title)).check(doesNotExist());
        }
        if (hasNavigation) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withText(R.string.stub_text_navigation)).check(matches(isDisplayed()));
        } else {
            onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
        }
    }

    public static void check(String title, int icon) {
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)), withText(title))).check(matches(isDisplayed()));
        onView(allOf(isAssignableFrom(ImageView.class), withParent(isAssignableFrom(Toolbar.class)), withDrawable(icon))).check(matches(isDisplayed()));
    }

}
