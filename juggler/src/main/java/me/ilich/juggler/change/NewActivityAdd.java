package me.ilich.juggler.change;

import android.content.ComponentName;
import android.content.Intent;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class NewActivityAdd implements Add.Interface {

    private final State state;
    private final Class<? extends JugglerActivity> activityClass;

    public NewActivityAdd(State state) {
        this.state = state;
        this.activityClass = null;
    }

    public NewActivityAdd(State state, Class<? extends JugglerActivity> activityClass) {
        this.state = state;
        this.activityClass = activityClass;
    }

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items, Intent intent) {
        if (activityClass == null) {
            intent.setComponent(new ComponentName(activity, JugglerActivity.class));
        }else{
            intent.setComponent(new ComponentName(activity, activityClass));
        }
        JugglerActivity.state(activity, state, intent);
        return null;
    }

}
