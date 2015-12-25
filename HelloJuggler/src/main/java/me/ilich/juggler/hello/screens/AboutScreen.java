package me.ilich.juggler.hello.screens;

import android.support.annotation.Nullable;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.AboutFragment;

public class AboutScreen extends Screen {

    @Nullable
    @Override
    protected JugglerToolbarFragment instanceToolbar() {
        return null;
    }

    @Nullable
    @Override
    protected JugglerContentFragment instanceContent() {
        return AboutFragment.create();
    }

}
