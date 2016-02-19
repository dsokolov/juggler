package me.ilich.juggler.hello.gui;

import android.os.Bundle;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.HelloState;
import me.ilich.juggler.hello.states.LoginState;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.SplashState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            //navigateTo().clearState(new MainState());
            navigateTo().clearState(new SplashState());
            //navigateTo().clearState(new LoginState());
        } else {
            navigateTo().restore();
        }
    }

}
