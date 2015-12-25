package me.ilich.juggler.hello.screens;

import android.support.annotation.Nullable;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ListFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

public class ListScreen extends Screen {

    @Nullable
    @Override
    protected JugglerToolbarFragment instanceToolbar() {
        return StandardToolbarFragment.create();
    }

    @Nullable
    @Override
    protected JugglerContentFragment instanceContent() {
        return ListFragment.create();
    }

}
