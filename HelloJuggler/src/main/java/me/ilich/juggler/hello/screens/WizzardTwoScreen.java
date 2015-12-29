package me.ilich.juggler.hello.screens;

import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizzardTwoFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(WizzardTwoFragment.class)
public interface WizzardTwoScreen extends Screen {

    void wizardThree();

}
