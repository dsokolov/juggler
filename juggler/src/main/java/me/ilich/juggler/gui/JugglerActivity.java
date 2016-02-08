package me.ilich.juggler.gui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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

    protected Navigable navigateTo() {
        return juggler;
    }

    @Override
    public void onBackPressed() {
        boolean b = juggler.backState();
        if (!b) {
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean b = juggler.upState();
        if (!b) {
            b = super.onSupportNavigateUp();
        }
        return b;
    }

}
