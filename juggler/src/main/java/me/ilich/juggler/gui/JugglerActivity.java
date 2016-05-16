package me.ilich.juggler.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.states.State;

public class JugglerActivity extends AppCompatActivity {

    private static final String STATE_JUGGLER = "state_juggler";
    private static final String EXTRA_STATE = "extra_state";

    public static Intent state(Context context, State<?> state) {
        Intent intent = new Intent(context, JugglerActivity.class);
        intent.putExtra(EXTRA_STATE, state);
        return intent;
    }

    private Juggler juggler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            juggler = new Juggler();
        } else {
            juggler = (Juggler) savedInstanceState.getSerializable(STATE_JUGGLER);
            if (juggler == null) {
                throw new RuntimeException("savedInstanceState should contains Juggler instance");
            }
        }
        juggler.setActivity(this);
        State<?> state = (State<?>) getIntent().getSerializableExtra(EXTRA_STATE);
        if (state != null) {
            if (savedInstanceState == null) {
                navigateTo().state(Add.deeper(state));
            } else {
                navigateTo().restore();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_JUGGLER, juggler);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        juggler.onPostCreate(savedInstanceState);
    }

    @VisibleForTesting
    public Navigable navigateTo() {
        return juggler;
    }

    @Override
    public void onBackPressed() {
        boolean b = juggler.onBackPressed();
        if (!b) {
            b = juggler.backState();
            if (!b) {
                finish();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = juggler.onUpPressed();
        if (!b) {
            b = juggler.upState();
            if (!b) {
                b = super.onSupportNavigateUp();
            }
        }
        return b;
    }

    @VisibleForTesting
    public Juggler getJuggler() {
        return juggler;
    }

}
