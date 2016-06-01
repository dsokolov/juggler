package me.ilich.juggler.change;

import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.gui.JugglerActivity;

public class RemoveCloseAllActivities implements Remove.Interface {

    @Override
    public Item remove(JugglerActivity activity, Stack<Item> items, Juggler.StateHolder currentStateHolder, Bundle data) {
        Intent intent = data.getParcelable(Juggler.DATA_NEW_ACTIVITY_INTENT);
        if (intent == null) {
            intent = new Intent();
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        data.putParcelable(Juggler.DATA_NEW_ACTIVITY_INTENT, intent);
        return null;
    }

}
