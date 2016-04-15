package me.ilich.juggler.gui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;

public class JugglerActivity extends AppCompatActivity {

    private static final String STATE_JUGGLER = "state_juggler";

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
        juggler.registerActivity(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        juggler.unregisterActivity(this);
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
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = juggler.upState();
        if (!b) {
            b = super.onSupportNavigateUp();
        }
        return b;
    }

    @VisibleForTesting
    public Juggler getJuggler() {
        return juggler;
    }

}
