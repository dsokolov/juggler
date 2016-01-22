package me.ilich.juggler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import me.ilich.juggler.transitions.Transition;

public class JugglerActivity extends AppCompatActivity {

    private Juggler juggler = Juggler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juggler.registerActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        juggler.unregisterActivity(this);
    }

    protected Juggler getJuggler() {
        return juggler;
    }

    @Override
    public void onBackPressed() {
        List<Juggler.TransitionBundle> transitionBundles = juggler.getBackTransitionBundles();
        if (transitionBundles.size() == 0) {
            super.onBackPressed();
        } else {
            Transition transition = transitionBundles.get(0).getTransition();
            transition.execute(juggler);
        }
    }
}
