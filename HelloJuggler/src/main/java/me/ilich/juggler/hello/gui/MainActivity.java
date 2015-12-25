package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.ilich.juggler.Action;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.R;
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

    @Override
    protected HelloScreensManager createScreenManager() {
        return new HelloScreensManager(this);
    }

    @Override
    public int getContainerContentLayoutId() {
        return R.id.container_content;
    }

    @Override
    public int getContainerToolbarLayoutId() {
        return R.id.container_toolbar;
    }
}
