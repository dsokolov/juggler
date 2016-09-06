package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class StaticMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Juggler.on(this).
                restore(savedInstanceState).
                state(MainState.main).
                execute();
    }

}