package me.ilich.juggler.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Action;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.screens.HelloScreensManager;

public class MainActivity extends JugglerActivity<HelloScreensManager> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean authorised = true;
        if(authorised) {
            getJuggler().getScreenManager().main();
        }else{
            getJuggler().getScreenManager().login();
        }
    }

}
