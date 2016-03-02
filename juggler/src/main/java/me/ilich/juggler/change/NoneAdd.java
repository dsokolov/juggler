package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class NoneAdd implements Add.Interface {

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items) {
        return null;
    }

}
