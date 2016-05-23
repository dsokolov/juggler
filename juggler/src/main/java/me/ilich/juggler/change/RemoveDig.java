package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveDig implements Remove.Interface {

    private final String tag;

    public RemoveDig(String tag) {
        this.tag = tag;
    }

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Intent intent, AtomicBoolean closeCurrentActivity) {
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
        if (newItem != null) {
            StateChanger.processContainersVisibility(activity, newItem);
            activity.getSupportFragmentManager().popBackStack(newItem.getTransactionName(), 0);
        }
        return newItem;
    }

}
