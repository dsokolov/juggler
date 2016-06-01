package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

class RemoveNone implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        return null;
    }

}
