package me.ilich.juggler.change;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class NewActivityAdd implements Add.Interface {

    private final State state;
    private final Class<? extends JugglerActivity> activityClass;
    @AnimRes
    private final int enterAnimationId;
    @AnimRes
    private final int exitAnimationId;

    public NewActivityAdd(State state) {
        this.state = state;
        this.activityClass = null;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass) {
        this.state = state;
        this.activityClass = activityClass;
        this.enterAnimationId = 0;
        this.exitAnimationId = 0;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass, @AnimRes int enterAnimationId, @AnimRes int exitAnimationId) {
        this.state = state;
        this.activityClass = activityClass;
        this.enterAnimationId = enterAnimationId;
        this.exitAnimationId = exitAnimationId;
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
        JugglerActivity.state(activity, state, intent);
        return null;
    }

}
