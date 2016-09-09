package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.staticjuggler.state.MainState;

public class StaticMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Juggler.on(this).create(savedInstanceState, new MainState(this));
    }

    @Override
    public void onBackPressed() {
        Juggler.on(this).backOrFinish();
    }

}