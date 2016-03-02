package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

class RemoveNone implements Remove.Interface {

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
        return null;
    }

}
