package me.ilich.juggler;

import android.support.v7.app.AppCompatActivity;

public class JugglerActivity<SM extends ScreensManager> extends AppCompatActivity {

    private Juggler<SM> juggler = new Juggler<>();

    protected Juggler<SM> getJuggler() {
        return juggler;
    }

}
