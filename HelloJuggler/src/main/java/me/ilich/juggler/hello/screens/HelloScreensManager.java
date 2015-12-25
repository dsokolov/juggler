package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Action;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;

public class HelloScreensManager extends ScreensManager {

    private final JugglerActivity<HelloScreensManager> activity;

    private SplashScreen helloScreen = new SplashScreen();
    private LoginScreen loginScreen = new LoginScreen();
    private RegistrationScreen registrationScreen = new RegistrationScreen();
    private MainScreen mainScreen = new MainScreen();
    private ListScreen listScreen = new ListScreen();
    private ItemDetailScreen itemDetailScreen = new ItemDetailScreen();
    private AboutScreen aboutScreen = new AboutScreen();

    public HelloScreensManager(JugglerActivity<HelloScreensManager> activity) {
        this.activity = activity;
    }

    @Override
    protected void onInit() {
        registrationScreen.setBack(Action.toScreen(loginScreen));
        registrationScreen.setUp(Action.toScreen(loginScreen));

        mainScreen.setBack((Screen) null);

        listScreen.setBack(mainScreen);

        itemDetailScreen.setBack(listScreen);

        aboutScreen.setBack(mainScreen);
    }

    public void main() {
        mainScreen.showOn(activity);
    }

    public void login() {
        loginScreen.showOn(activity);
    }

    public void list() {
        listScreen.showOn(activity);
    }

    public void itemDetails(int id) {
        itemDetailScreen.setParams(id);
        itemDetailScreen.showOn(activity);
    }

    public void about() {
        aboutScreen.showOn(activity);
    }

}
