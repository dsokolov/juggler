package me.ilich.juggler.change;

import java.io.Serializable;
import java.util.Stack;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public class StateChanger implements Serializable {

    public static String generateTransactionName(State oldState, State newState) {
        String fromStr = oldState == null ? "null" : oldState.getClass().getSimpleName();
        String toStr = newState == null ? "null" : newState.getClass().getSimpleName();
        String hashStr = UUID.randomUUID().toString();
        return String.format("%s -> %s (%s)", fromStr, toStr, hashStr);
    }

    private Stack<Item> items = new Stack<>();

    public Stack<Item> getItems() {
        return items;
    }

    @VisibleForTesting
    public int getStackLength() {
        return items.size();
    }

    public State transaction(String transactionName, JugglerActivity activity, @Nullable String tag) {
        Item newItem = null;
        State oldState = null;
        if (!items.isEmpty()) {
            Item oldItem = items.peek();
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
            if (oldItem != null) {
                oldState = oldItem.getState();
            }
        }
        final State newState;
        if (newItem == null) {
            newState = null;
        } else {
            newState = newItem.getState();
//            processContainersVisibility(activity, newItem);
            activity.getSupportFragmentManager().popBackStack(newItem.getTransactionName(), 0);
        }
        processStateChange(activity, oldState, newState);
        return newState;
    }

/*    public static void processContainersVisibility(JugglerActivity activity, Item newItem) {
        for (int id : newItem.getVisibleIds()) {
            View v = activity.findViewById(id);
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            }
        }
        for (int id : newItem.getGoneIds()) {
            View v = activity.findViewById(id);
            if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }*/

    public State restore(JugglerActivity activity) {
        Item item = items.peek();
        activity.setContentView(activity.getJuggler().getLayoutId());
        processStateChange(activity, null, item.getState());
        return item.getState();
    }

    private void processStateChange(JugglerActivity activity, @Nullable State oldState, @Nullable State newState) {
        if (oldState != null) {
            oldState.onDeactivate(activity); //TODO call when all fragments stopped
        }
    }

}
