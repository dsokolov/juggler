package me.ilich.juggler.hello;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.states.ItemDetailsState;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Juggler.TransitionBundle> transitionBundles = Juggler.getInstance().getStartTransitionBundles();
        Log.v("Sokolov", transitionBundles + "");
        getJuggler().changeState(new ItemDetailsState(123));
    }

}
