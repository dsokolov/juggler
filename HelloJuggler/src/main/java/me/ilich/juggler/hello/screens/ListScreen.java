package me.ilich.juggler.hello.screens;

import android.support.annotation.Nullable;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ListFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

public class ListScreen extends Screen<Screen.Params> {


    @Override
    protected Class<? extends JugglerToolbarFragment> toolbarClass() {
        return StandardToolbarFragment.class;
    }

    @Override
    protected Class<? extends JugglerContentFragment> contentClass() {
        return ListFragment.class;
    }

}
