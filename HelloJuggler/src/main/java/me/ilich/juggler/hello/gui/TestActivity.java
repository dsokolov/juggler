package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import me.ilich.juggler.gui.JugglerActivity;

public class TestActivity extends JugglerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.v("Sokolov", "onCreate");
    }

}
