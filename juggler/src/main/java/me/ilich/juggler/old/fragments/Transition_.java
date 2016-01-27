package me.ilich.juggler.old.fragments;

import android.support.annotation.Nullable;

import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;

public class Transition_ {

    private final Class<? extends Screen> source;
    private final Class<? extends Screen> destination;
    private final ScreensManager.MODE mode;
    private final String stack;

    public Transition_(Class<? extends Screen> source, Class<? extends Screen> destination, ScreensManager.MODE mode, String stack) {
        this.source = source;
        this.destination = destination;
        this.stack = stack;
        this.mode = mode;
    }

    void execute(JugglerActivity_ activity, ScreensManager screensManager, @Nullable Screen.Params params) {
        //screensManager.show(activity, mode, stack, destination, params);
    }

    public Class<? extends Screen> getSource() {
        return source;
    }

}
