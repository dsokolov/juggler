package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class JugglerActivity_ extends AppCompatActivity {

    private Juggler_ juggler;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juggler = Juggler_.getInstance();
        juggler.onCreate(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!isFinishing()) {
            juggler.onSaveInstanceState(this, outState);
            juggler.onDestroy(this);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            juggler.onDestroy(this);
        }
    }

    public final Juggler_ getJuggler() {
        return juggler;
    }

    @Override
    public void onBackPressed() {
        boolean b = juggler.onBack(this);
        if (!b) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = juggler.onUp(this);
        if (!b) {
            b = super.onSupportNavigateUp();
        }
        return b;
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        if (juggler.getScreenManager().getToolbarFragment() == null) {
            super.onTitleChanged(title, color);
        } else {
            if (juggler.getScreenManager().getToolbarFragment().isCustomToolbar()) {
                juggler.setToolbarTitle(title, color);
            } else {
                super.onTitleChanged(title, color);
            }
        }
    }

}
