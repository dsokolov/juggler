package me.ilich.juggler.hello;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.states.ItemDetailsState;
import me.ilich.juggler.states.State;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJuggler().changeState(new ItemDetailsState(123));
    }

}
