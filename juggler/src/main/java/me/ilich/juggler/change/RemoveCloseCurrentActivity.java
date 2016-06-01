package me.ilich.juggler.change;

import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

public class RemoveCloseCurrentActivity implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        data.putBoolean(Juggler.DATA_CLOSE_CURRENT_ACTIVITY, true);
        return null;
    }

}
