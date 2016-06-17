package me.ilich.juggler.usage.navigate;

import me.ilich.juggler.usage.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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
            onView(withText(R.string.stub_text_navigation)).check(matches(isDisplayed()));
        } else {
            onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
        }
    }

}
