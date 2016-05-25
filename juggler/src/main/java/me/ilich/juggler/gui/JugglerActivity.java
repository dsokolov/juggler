package me.ilich.juggler.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.CrossActivity;
import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.states.State;

public class JugglerActivity extends AppCompatActivity {

    private static final String STATE_JUGGLER = "state_juggler";
    private static final String EXTRA_STATE = "extra_state";

    public static Intent state(Context context, State<?> state, @Nullable Intent intent) {
        if (intent == null) {
            intent = new Intent(context, JugglerActivity.class);
        }
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

        final State<?> state;
        if (getIntent().hasExtra(EXTRA_STATE)) {
            state = (State<?>) getIntent().getSerializableExtra(EXTRA_STATE);
        } else {
            state = createState();
        }
        if (state != null) {
            if (savedInstanceState == null) {
                navigateTo().state(Add.deeper(state));
            } else {
                navigateTo().restore();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Remove.Interface remove;
        final Add.Interface add;
        if (CrossActivity.getInstance().hasRemove()) {
            remove = CrossActivity.getInstance().getRemove();
        } else {
            remove = null;
        }
        if (CrossActivity.getInstance().hasAdd()) {
            add = CrossActivity.getInstance().getAdd();
        } else {
            add = null;
        }
        navigateTo().state(remove, add);
    }

    protected State createState() {
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_JUGGLER, juggler);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        juggler.activateCurrentState();
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
                if (!b) {
                    finish();
                }
            }
        }
        return b;
    }

    @VisibleForTesting
    public Juggler getJuggler() {
        return juggler;
    }

}
