package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizzardThreeFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(WizzardThreeFragment.class)
public class WizzardThreeScreen extends Screen<Screen.Params> {

}
