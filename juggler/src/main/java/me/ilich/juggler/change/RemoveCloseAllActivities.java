package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

public class RemoveCloseAllActivities implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Intent intent, AtomicBoolean closeCurrentActivity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return null;
    }

}
