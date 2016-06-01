package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

class NoneAdd implements Add.Interface {

    @Override
    public Item add(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle bundle) {
        return null;
    }

}
