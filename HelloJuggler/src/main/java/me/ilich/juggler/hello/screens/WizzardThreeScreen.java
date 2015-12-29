package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizzardThreeFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(WizzardThreeFragment.class)
public interface WizzardThreeScreen extends Screen {

}
