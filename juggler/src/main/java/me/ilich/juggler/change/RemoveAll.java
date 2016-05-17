package me.ilich.juggler.change;

import android.content.Intent;
import android.support.v4.app.FragmentManager;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveAll implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Intent intent, AtomicBoolean closeCurrentActivity) {
        if (items.empty()) {

        } else {
            String firstTransactionName = items.get(0).getTransactionName();
            items.clear();
            FragmentManager fm = activity.getSupportFragmentManager();
            fm.popBackStack(firstTransactionName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        return null;
    }

}
