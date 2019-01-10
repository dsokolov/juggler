package me.ilich.juggler.usage.navigate;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import org.hamcrest.Matcher;

import androidx.test.espresso.intent.Intents;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubSetResultContentFragment;
import me.ilich.juggler.usage.fragments.StubStartForResultContentFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ActivityForResult extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public ActivityForResult() {
        super(JugglerActivity.class);
    }

    public void testForResult() {
        int requestCode = 123;
        int resultCode = Activity.RESULT_OK;

        State stateA = StateFactory.contentOnlyState(StubStartForResultContentFragment.class);
        State stateB = StateFactory.contentOnlyState(StubSetResultContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, stateA));
        getActivity();


        Matcher<Intent> expectedIntent = allOf(
                hasComponent(new ComponentName(getInstrumentation().getContext(), JugglerActivity.class)),
                hasExtraWithKey(JugglerActivity.EXTRA_STATE));

        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(resultCode, new Intent());
        Intents.init();
        intending(expectedIntent).respondWith(activityResult);
        getActivity().navigateTo().state(Add.newActivityForResult(stateB, JugglerActivity.class, requestCode));
        intended(expectedIntent);
        Intents.release();
        onView(withId(R.id.request_code)).check(matches(withText(Integer.toString(requestCode))));
        onView(withId(R.id.result_code)).check(matches(withText(Integer.toString(resultCode))));
    }


}
