package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

public final class Remove {

    public static Remove.Interface none() {
        return new RemoveNone();
    }

    public static Remove.Interface all() {
        return new RemoveAll();
    }

    public static Remove.Interface dig(String tag) {
        return new RemoveDig(tag);
    }

    public static Remove.Interface last() {
        return new RemoveLast();
    }

    public static Remove.Interface closeCurrentActivity() {
        return new RemoveCloseCurrentActivity();
    }

    public static Remove.Interface closeAllActivities() {
        return new RemoveCloseAllActivities();
    }

    private Remove() {

    }

    public interface Interface {

        Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data);

    }


}
