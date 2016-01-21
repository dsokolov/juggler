package me.ilich.juggler.hello;

import android.app.Application;

import me.ilich.juggler.hello.states.AboutState;
import me.ilich.juggler.hello.states.ItemDetailsState;
import me.ilich.juggler.states.ClosedSystemState;
import me.ilich.juggler.Juggler;
import me.ilich.juggler.Juggler_;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.screens.AboutScreen;
import me.ilich.juggler.hello.screens.HelloScreensManager;
import me.ilich.juggler.hello.screens.MainScreen;
import me.ilich.juggler.hello.states.CategoriesState;

public class HelloJugglerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Juggler_.init(new HelloScreensManager());
        Juggler_.getInstance().registerScreen(MainScreen.class);
        Juggler_.getInstance().registerScreen(AboutScreen.class);

        Juggler_.getInstance().setStart(MainScreen.class, null);

        Juggler.init();
        Juggler.getInstance().registerTransition(ClosedSystemState.class, ItemDetailsState.class, Transition.clearStackAndAdd("default_stack"));
    }

}
