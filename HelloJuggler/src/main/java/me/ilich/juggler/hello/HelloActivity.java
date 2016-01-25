package me.ilich.juggler.hello;

import android.os.Bundle;

import me.ilich.juggler.JugglerActivity;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigateTo().firstState();
        } else {
            navigateTo().currentState();
        }
    }

}
