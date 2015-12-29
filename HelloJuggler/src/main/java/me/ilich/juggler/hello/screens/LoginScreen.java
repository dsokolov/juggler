package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.LoginFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(LoginFragment.class)
public interface LoginScreen extends Screen {

    void main();

}
