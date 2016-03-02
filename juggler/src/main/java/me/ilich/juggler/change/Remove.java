package me.ilich.juggler.change;

import java.util.Stack;

import me.ilich.juggler.gui.JugglerActivity;

public final class Remove {

    public static Remove.Interface none() {
        return new RemoveNone();
    }

    public static Remove.Interface clear() {
        return new RemoveClear();
    }

    public static Remove.Interface dig(String tag) {
        return new RemoveDig(tag);
    }

    public static Remove.Interface last() {
        return new RemoveLast();
    }

    private Remove() {

    }

    public interface Interface {

        Item pop(JugglerActivity activity, Stack<Item> items);

    }


}
