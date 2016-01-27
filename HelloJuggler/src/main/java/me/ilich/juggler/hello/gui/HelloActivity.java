package me.ilich.juggler.hello.gui;

import android.os.Bundle;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.states.MainState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigateTo().changeState(new MainState());
        } else {
            navigateTo().currentState();
        }
    }

}
