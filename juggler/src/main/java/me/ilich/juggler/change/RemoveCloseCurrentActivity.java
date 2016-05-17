package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import me.ilich.juggler.gui.JugglerActivity;

public class RemoveCloseCurrentActivity implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Intent intent, AtomicBoolean closeCurrentActivity) {
        closeCurrentActivity.set(true);
        return null;
    }

}
