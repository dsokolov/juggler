package me.ilich.juggler.change;

import android.content.Intent;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class NoneAdd implements Add.Interface {

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items, Intent intent) {
        return null;
    }

}
