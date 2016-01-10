package me.ilich.juggler.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.ScreensManager;

public abstract class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private Juggler<SM> juggler;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juggler = new Juggler<>(createScreenManager(), this);
        juggler.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        juggler.onSaveInstanceState(outState);
        juggler.onDestroy();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract SM createScreenManager();

    public final Juggler<SM> getJuggler() {
        return juggler;
    }

    @Override
    public void onBackPressed() {
        boolean b = juggler.onBack();
        if (!b) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = juggler.onUp();
        if (!b) {
            b = super.onSupportNavigateUp();
        }
        return b;
    }

}
