package me.ilich.juggler.change;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import me.ilich.juggler.Transition;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public class StateChanger {

    public static String generateTransactionName(State oldState, State newState) {
        String fromStr = oldState == null ? "null" : oldState.getClass().getSimpleName();
        String toStr = newState == null ? "null" : newState.getClass().getSimpleName();
        String hashStr = UUID.randomUUID().toString();
        return String.format("%s -> %s (%s)", fromStr, toStr, hashStr);
    }

    private Stack<Item> items = new Stack<>();

    public State change(JugglerActivity activity, PopCondition popStateCondition, Add addCondition) {
        return doChange(activity, popStateCondition, addCondition);
    }

    @Nullable
    private State doChange(JugglerActivity activity, PopCondition popStateCondition, Add addCondition) {
        if (activity == null) {
            throw new NullPointerException("activity");
        }

        final Item resultItem;
        final Item oldItem;
        if (items.empty()) {
            oldItem = null;
        } else {
            oldItem = items.peek();
        }
        State oldState = oldItem == null ? null : oldItem.getState();

        boolean hasPop = popStateCondition != null;
        boolean hasAdd = addCondition != null;

        if (hasPop && hasAdd) {
            popStateCondition.pop(activity, items);
            resultItem = addCondition.add(activity, items);
        } else if (hasPop) {
            resultItem = popStateCondition.pop(activity, items);
        } else if (hasAdd) {
            resultItem = addCondition.add(activity, items);
        } else {
            if (items.empty()) {
                resultItem = null;
            } else {
                resultItem = items.peek();
            }
        }

        final State resultState;
        if (resultItem == null) {
            resultState = null;
        } else {
            resultState = resultItem.getState();
        }
        processStateChange(activity, oldState, resultState);

        return resultState;
    }

    public State transaction(String transactionName, JugglerActivity activity, @Nullable String tag) {
        Item oldItem = items.peek();
        Item newItem = null;
        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.getTransactionName().equals(transactionName)) {
                    newItem = item;
                    work = false;
                } else {
                    items.pop();
                }
            }
        }
        final State newState;
        if (newItem == null) {
            newState = null;
        } else {
            newState = newItem.getState();
            if (oldItem.getLayoutId() != newItem.getLayoutId()) {
                activity.setContentView(newItem.getLayoutId());
            }
            activity.getSupportFragmentManager().popBackStack(newItem.getTransactionName(), 0);
        }
        processStateChange(activity, oldItem.getState(), newState);
        return newState;
    }

    public State restore(JugglerActivity activity) {
        Item item = items.peek();
        activity.setContentView(item.getLayoutId());
        processStateChange(activity, null, item.getState());
        return item.getState();
    }

    private void processStateChange(JugglerActivity activity, @Nullable State oldState, @Nullable State newState) {
        if (oldState != null) {
            oldState.onDeactivate(activity);
        }
        if (newState != null) {
            newState.onActivate(activity);
        }
    }

}
