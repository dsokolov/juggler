package me.ilich.juggler.change;

import android.support.v4.app.FragmentManager;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveLast implements Remove.Interface {

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
        final Item oldItem = items.pop();
        final Item newItem;
        if (items.isEmpty()) {
            newItem = null;
        } else {
            newItem = items.peek();
        }
        if (newItem != null && newItem.getLayoutId() != oldItem.getLayoutId()) {
            activity.setContentView(newItem.getLayoutId());
        }
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStackImmediate();
        return newItem;
    }

}
