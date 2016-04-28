package me.ilich.juggler.gui;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Navigable;

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

}
