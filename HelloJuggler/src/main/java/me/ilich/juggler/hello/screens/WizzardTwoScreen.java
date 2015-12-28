package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizzardOneFragment;
import me.ilich.juggler.hello.gui.WizzardTwoFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(WizzardTwoFragment.class)
public class WizzardTwoScreen extends Screen<Screen.Params> {

}
