package me.ilich.juggler.change;

import android.support.v4.app.FragmentManager;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveClear implements Remove.Interface {

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
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
