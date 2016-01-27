package me.ilich.juggler.hello.old.screens;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.old.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.old.gui.LoginFragment;
import me.ilich.juggler.hello.old.gui.StandardToolbarFragment;

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
