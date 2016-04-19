package me.ilich.juggler.hello.gui.activities;

import android.os.Bundle;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.LoginState;

public class SlaveActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigateTo().state(Add.deeper(new LoginState()));
        } else {
            navigateTo().restore();
        }
    }

}
