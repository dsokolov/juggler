package me.ilich.juggler.hello.gui;

import android.os.Bundle;

import me.ilich.juggler.change.ClearPopCondition;
import me.ilich.juggler.change.DeeperAdd;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.MainState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            //navigateTo().clearState(new MainState());
            navigateTo().state(new ClearPopCondition(), new DeeperAdd(new MainState(), null));

            //navigateTo().clearState(new SplashState());
            //navigateTo().clearState(new LoginState());
        } else {
            navigateTo().restore();
        }
    }

}
