package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

class RemoveDig implements Remove.Interface {

    private final String tag;

    public RemoveDig(String tag) {
        this.tag = tag;
    }

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
        Item oldItem = items.peek();
        Item newItem = null;
        boolean work = true;
        while (work) {
            if (items.isEmpty()) {
                work = false;
            } else {
                Item item = items.peek();
                if (item.getTag() != null && item.getTag().equals(tag)) {
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
            activity.getSupportFragmentManager().popBackStackImmediate(newItem.getTransactionName(), 0);
        }
        return newItem;
    }

}
