package me.ilich.juggler.hello.screens;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.ScreensManager;

public class HelloScreensManager extends ScreensManager implements
        MainScreen,
        ListScreen,
        WizardOneScreen,
        WizardTwoScreen,
        WizardThreeScreen,
        LoginScreen {

    public HelloScreensManager(JugglerActivity<HelloScreensManager> activity) {
        super(activity);
    }

    public void main() {
        show(SHOW_MODE.CLEAR, MainScreen.class);
    }

    @Override
    public void login() {
        show(SHOW_MODE.ADD, LoginScreen.class);
    }

    @Override
    public void toolbarExplain() {
        show(SHOW_MODE.ADD, ToolbarExplainScreen.class);
    }

    @Override
    public void list() {
        show(SHOW_MODE.ADD, ListScreen.class);
    }

    @Override
    public void itemDetails(int id) {
        show(SHOW_MODE.ADD, ItemDetailScreen.class, new ItemDetailScreen.Params(id));
    }

    @Override
    public void wizardOne() {
        show(SHOW_MODE.ADD, WizardOneScreen.class);
    }

    @Override
    public void wizardTwo() {
        show(SHOW_MODE.ADD, WizardTwoScreen.class);
    }

    @Override
    public void wizardThree() {
        show(SHOW_MODE.ADD, WizardThreeScreen.class);
    }

    @Override
    public void about() {
        show(SHOW_MODE.ADD, AboutScreen.class);
    }

}
