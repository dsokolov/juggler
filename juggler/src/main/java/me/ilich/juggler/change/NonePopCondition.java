package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

public class NonePopCondition implements PopCondition {

    @Override
    public Item pop(JugglerActivity activity, Stack<Item> items) {
        return null;
    }
}
