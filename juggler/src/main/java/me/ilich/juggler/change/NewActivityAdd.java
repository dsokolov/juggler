package me.ilich.juggler.change;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class NewActivityAdd implements Add.Interface {

    public static NewActivityAdd forResult(State state, Class<? extends JugglerActivity> activityClass, int requestCode) {
        return new NewActivityAdd(state, activityClass, 0, 0, true, requestCode);
    }

    private final State state;
    private final Class<? extends JugglerActivity> activityClass;
    @AnimRes
    private final int enterAnimationId;
    @AnimRes
    private final int exitAnimationId;

    private final boolean isForResult;
    private final int requestCode;

    @Nullable
    private Bundle activityOptions;

    public NewActivityAdd(State state) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = null;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
        this.isForResult = false;
        this.requestCode = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
        this.isForResult = false;
        this.requestCode = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = enterAnimationId;
        this.exitAnimationId = exitAnimationId;
        this.isForResult = false;
        this.requestCode = 0;
    }

    private NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId, boolean forResult, int requestCode) {
        this.state = state;
        this.activityOptions = state.getActivityOptions();
        this.activityClass = activityClass;
        this.enterAnimationId = enterAnimationId;
        this.exitAnimationId = exitAnimationId;
        this.isForResult = true;
        this.requestCode = requestCode;
    }

    @Nullable
    public Bundle getActivityOptions() {
        return activityOptions;
    }

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle bundle) {
        Intent intent = bundle.getParcelable(Juggler.DATA_NEW_ACTIVITY_INTENT);
        if (intent == null) {
            intent = new Intent();
        }
        if (activityClass == null) {
            intent.setComponent(new ComponentName(activity, JugglerActivity.class));
        } else {
            intent.setComponent(new ComponentName(activity, activityClass));
        }
        bundle.putParcelable(Juggler.DATA_NEW_ACTIVITY_INTENT, intent);
        bundle.putInt(Juggler.DATA_ANIMATION_START_ENTER, enterAnimationId);
        bundle.putInt(Juggler.DATA_ANIMATION_START_EXIT, exitAnimationId);
        bundle.putBoolean(Juggler.DATA_IS_FOR_RESULT, isForResult);
        bundle.putInt(Juggler.DATA_REQUEST_CODE, requestCode);
        JugglerActivity.state(activity, state, intent);
        return null;
    }

}
