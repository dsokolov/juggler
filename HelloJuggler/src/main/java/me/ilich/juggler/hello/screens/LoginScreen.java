package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.LoginFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

@JugglerToolbar(StandardToolbarFragment.class)
@JugglerContent(LoginFragment.class)
public class LoginScreen extends Screen {

    public LoginScreen(Navigator_ navigator) {
        super(navigator);
    }

    public void main() {
        //TODO
    }

}
