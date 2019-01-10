package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import androidx.fragment.app.FragmentManager;
import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

class RemoveAll implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        if (items.empty()) {
            currentStateHolder.set(null);
        } else {
            String firstTransactionName = items.get(0).getTransactionName();
            items.clear();
            currentStateHolder.set(null);
            activity.getSupportFragmentManager().popBackStackImmediate(firstTransactionName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        return null;
    }

}
