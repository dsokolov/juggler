package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.AboutFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(AboutFragment.class)
public interface AboutScreen extends Screen {

}
