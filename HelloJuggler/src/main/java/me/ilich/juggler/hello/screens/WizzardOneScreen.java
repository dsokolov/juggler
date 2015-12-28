package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizzardOneFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(WizzardOneFragment.class)
public class WizzardOneScreen extends Screen<Screen.Params> {

}
