package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

public interface PopCondition {

    Item pop(JugglerActivity activity, Stack<Item> items);

}
