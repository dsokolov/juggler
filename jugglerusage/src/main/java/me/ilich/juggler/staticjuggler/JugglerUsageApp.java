package me.ilich.juggler.staticjuggler;

import android.app.Application;

public class JugglerUsageApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Juggler.on(this).init();
    }

}
