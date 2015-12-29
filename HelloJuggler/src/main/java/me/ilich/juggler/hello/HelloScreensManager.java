package me.ilich.juggler.hello;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.hello.screens.AboutScreen;
import me.ilich.juggler.hello.screens.ItemDetailScreen;
import me.ilich.juggler.hello.screens.ListScreen;
import me.ilich.juggler.hello.screens.LoginScreen;
import me.ilich.juggler.hello.screens.MainScreen;
import me.ilich.juggler.hello.screens.WizzardOneScreen;
import me.ilich.juggler.hello.screens.WizzardThreeScreen;
import me.ilich.juggler.hello.screens.WizzardTwoScreen;

public class HelloScreensManager extends ScreensManager implements
        MainScreen,
        ListScreen,
        WizzardOneScreen,
        WizzardTwoScreen,
        WizzardThreeScreen,
        LoginScreen {

    public HelloScreensManager(JugglerActivity<HelloScreensManager> activity) {
        super(activity);
    }

    public void main() {
        showNew(MainScreen.class);
    }

    @Override
    public void login() {
        showNew(LoginScreen.class);
    }

    @Override
    public void list() {
        showNew(ListScreen.class);
    }

    @Override
    public void itemDetails(int id) {
        showNew(ItemDetailScreen.class, new ItemDetailScreen.Params(id));
    }

    @Override
    public void wizardOne() {
        showNew(WizzardOneScreen.class);
    }

    @Override
    public void wizardTwo() {
        showNew(WizzardTwoScreen.class);
    }

    @Override
    public void wizardThree() {
        showNew(WizzardThreeScreen.class);
    }

    @Override
    public void about() {
        showNew(AboutScreen.class);
    }

}
