package me.ilich.juggler.hello.gui.activities;

import android.os.Bundle;
import android.util.Log;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.SplashState;
import me.ilich.juggler.hello.states.StateA;
import me.ilich.juggler.hello.states.WizardOneState;
import me.ilich.juggler.states.State;

public class HelloActivity extends JugglerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return new SplashState();
    }
}
