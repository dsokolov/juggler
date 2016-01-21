package me.ilich.juggler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

    protected Juggler getJuggler() {
        return juggler;
    }

}
