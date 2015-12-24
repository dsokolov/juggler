package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Action;
import me.ilich.juggler.ScreensManager;

public class HelloScreensManager extends ScreensManager {

    private SplashScreen helloScreen = new SplashScreen();
    private LoginScreen loginScreen = new LoginScreen();
    private RegistrationScreen registrationScreen = new RegistrationScreen();
    private MainScreen mainScreen = new MainScreen();

    @Override
    protected void onInit() {

        registrationScreen.setBack(Action.toScreen(loginScreen));
        registrationScreen.setUp(Action.toScreen(loginScreen));

    }

    public void main() {
        Action.toScreen(mainScreen);
    }

    public void login() {
        Action.toScreen(loginScreen);
    }

}
