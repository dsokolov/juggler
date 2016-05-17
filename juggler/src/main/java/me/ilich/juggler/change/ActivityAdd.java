package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class ActivityAdd implements Add.Interface {

    private final State state;
    private final Class<JugglerActivity> activityClass;

    public ActivityAdd(State state) {
        this.state = state;
        this.activityClass = null;
    }

    public ActivityAdd(State state, Class<JugglerActivity> activityClass) {
        this.state = state;
        this.activityClass = activityClass;
    }

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items) {
        Intent intent = null;
        if (activityClass != null) {
            intent = new Intent(activity, activityClass);
        }
        intent = JugglerActivity.state(activity, state, intent);
        activity.startActivity(intent);
        return null;
    }

}
