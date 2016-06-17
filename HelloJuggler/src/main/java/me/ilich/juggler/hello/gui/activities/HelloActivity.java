package me.ilich.juggler.hello.gui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import me.ilich.juggler.Log;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.LoginState;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.StateB;
import me.ilich.juggler.states.State;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.v(this, "onBackStackChanged");
                getJuggler().dump();
            }
        });
/*        if (savedInstanceState == null) {
            //navigateTo().state(Remove.none(), Add.deeper(new StateA()));

            //navigateTo().state(Remove.all(), Add.deeper(new TabsState()));
            //navigateTo().clearState(new WizardOneState());
            //navigateTo().state(Add.deeper(new MainState()));
            //navigateTo().state(Remove.all(), Add.deeper(new MainState()));

            navigateTo().state(Add.deeper(new SplashState()));
            //navigateTo().clearState(new LoginState());
        } else {
            navigateTo().restore();
        }*/
    }

    @Override
    protected State createState() {
        return //new SplashState();
                new MainState();
        //new LoginState();
        //new StateB();
        //new Main2State();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

}
