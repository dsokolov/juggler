package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class StaticMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Juggler.on(this).create(savedInstanceState, States.main(this));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Juggler.on(this).save(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Juggler.on(this).destroy();
    }

    @Override
    public void onBackPressed() {
        Juggler.on(this).backOrFinish();
    }

}