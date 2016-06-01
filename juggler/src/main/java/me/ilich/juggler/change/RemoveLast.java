package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

class RemoveLast implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        final Item oldItem = items.pop();
        final Item newItem;
        if (items.isEmpty()) {
            newItem = null;
        } else {
            newItem = items.peek();
        }
        if (newItem == null) {
            currentStateHolder.set(null);
        } else {
            currentStateHolder.set(newItem.getState());
        }
        activity.getSupportFragmentManager().popBackStackImmediate();
        return newItem;
    }

}
