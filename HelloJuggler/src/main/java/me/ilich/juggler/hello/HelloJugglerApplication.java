package me.ilich.juggler.hello;

import android.app.Application;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.Juggler_;
import me.ilich.juggler.hello.screens.AboutScreen;
import me.ilich.juggler.hello.screens.HelloScreensManager;
import me.ilich.juggler.hello.screens.MainScreen;
import me.ilich.juggler.hello.states.AboutState;
import me.ilich.juggler.hello.states.ItemDetailsState;
import me.ilich.juggler.hello.states.ItemsListState;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.transitions.CurrentStackPopTransition;
import me.ilich.juggler.transitions.CurrentStackPushTransition;

public class HelloJugglerApplication extends Application {

    private static final String STACK = "default_stack";

    @Override
    public void onCreate() {
        super.onCreate();
        Juggler_.init(new HelloScreensManager());
        Juggler_.getInstance().registerScreen(MainScreen.class);
        Juggler_.getInstance().registerScreen(AboutScreen.class);
        Juggler_.getInstance().setStart(MainScreen.class, null);

        Juggler.init();
        //Juggler.getInstance().getTransactionsRepository().registerStartup(new AboutState(), STACK);
        Juggler.getInstance().getTransactionsRepository().registerStartup(new MainState(), STACK);

        Juggler.getInstance().getTransactionsRepository().registerBack(MainState.class, new CurrentStackPopTransition());
        Juggler.getInstance().getTransactionsRepository().registerTransition(MainState.class, ItemsListState.class, new CurrentStackPushTransition());
        Juggler.getInstance().getTransactionsRepository().registerTransition(MainState.class, AboutState.class, new CurrentStackPushTransition());

        Juggler.getInstance().getTransactionsRepository().registerBack(ItemsListState.class, new CurrentStackPopTransition());
        Juggler.getInstance().getTransactionsRepository().registerTransition(ItemsListState.class, ItemDetailsState.class, new CurrentStackPushTransition());

        Juggler.getInstance().getTransactionsRepository().registerBack(ItemDetailsState.class, new CurrentStackPopTransition());

        Juggler.getInstance().getTransactionsRepository().registerBack(AboutState.class, new CurrentStackPopTransition());
        Juggler.getInstance().getTransactionsRepository().registerUp(AboutState.class, new CurrentStackPopTransition());

    }

}
