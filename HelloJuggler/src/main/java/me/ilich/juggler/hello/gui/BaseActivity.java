package me.ilich.juggler.hello.gui;

import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.hello.screens.HelloScreensManager;

public class BaseActivity extends JugglerActivity<HelloScreensManager> {

    @Override
    protected HelloScreensManager createScreenManager() {
        return new HelloScreensManager(this);
    }

}
