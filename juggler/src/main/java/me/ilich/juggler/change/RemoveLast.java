package me.ilich.juggler.change;

import android.support.v4.app.FragmentManager;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveLast implements Remove.Interface {

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
        items.pop();
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack();
        final Item item;
        if (items.isEmpty()) {
            item = null;
        } else {
            item = items.peek();
        }
        return item;
    }

}
