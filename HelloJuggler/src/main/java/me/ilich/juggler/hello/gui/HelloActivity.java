package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.view.MenuItem;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.MainState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigateTo().clearState(new MainState());
        } else {
            navigateTo().restore();
        }
    }

}
