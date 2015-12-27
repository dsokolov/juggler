package me.ilich.juggler.hello;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.hello.screens.ItemDetailScreen;
import me.ilich.juggler.hello.screens.ListScreen;
import me.ilich.juggler.hello.screens.MainScreen;

public class HelloScreensManager extends ScreensManager {

    private final JugglerActivity<HelloScreensManager> activity;

    /*    private SplashScreen helloScreen = new SplashScreen();
        private LoginScreen loginScreen = new LoginScreen();
        private RegistrationScreen registrationScreen = new RegistrationScreen();*/
    private MainScreen mainScreen = new MainScreen();
    private ListScreen listScreen = new ListScreen();
    private ItemDetailScreen itemDetailScreen = new ItemDetailScreen();
    /*private AboutScreen aboutScreen = new AboutScreen();*/

    public HelloScreensManager(JugglerActivity<HelloScreensManager> activity) {
        this.activity = activity;
    }

    @Override
    protected void onInit() {
/*        registrationScreen.setBack(Action.toScreen(loginScreen));
        registrationScreen.setUp(Action.toScreen(loginScreen));

        mainScreen.setBack((Screen) null);

        listScreen.setBack(mainScreen);

        itemDetailScreen.setBack(listScreen);

        aboutScreen.setBack(mainScreen);*/
    }

    public void main() {
        Screen.Instance instance = mainScreen.create(new Screen.Params());
        show(instance, activity);
    }

/*    public void login() {
        loginScreen.showOn(activity);
    }*/

    public void list() {
        Screen.Instance instance = listScreen.create(new Screen.Params());
        show(instance, activity);
    }

    public void itemDetails(int id) {
        Screen.Instance instance = itemDetailScreen.create(new ItemDetailScreen.Params(id));
        show(instance, activity);
    }

/*    public void about() {
        aboutScreen.showOn(activity);
    }*/

}
