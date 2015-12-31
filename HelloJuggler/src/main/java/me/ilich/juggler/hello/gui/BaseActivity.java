package me.ilich.juggler.hello.gui;

import android.os.Bundle;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.screens.HelloScreensManager;
import me.ilich.juggler.hello.R;

public class BaseActivity extends JugglerActivity<HelloScreensManager> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean authorised = true;
        if (authorised) {
            navigateTo(HelloScreensManager.class).main();
        } else {
            //getScreensManager().login();
        }
    }

    @Override
    protected HelloScreensManager createScreenManager() {
        return new HelloScreensManager(this);
    }

}
