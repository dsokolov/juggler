package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerContent;
import me.ilich.juggler.JugglerToolbar;
import me.ilich.juggler.JugglerToolbarFragment;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.ListFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(aClass = StandardToolbarFragment.class)
@JugglerContent(aClass = ListFragment.class)
public class ListScreen extends Screen<Screen.Params> {

}
