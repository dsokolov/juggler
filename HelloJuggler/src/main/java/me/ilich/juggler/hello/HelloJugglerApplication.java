package me.ilich.juggler.hello;

import android.app.Application;

import me.ilich.juggler.Juggler;

public class HelloJugglerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Juggler.init();
    }

}
