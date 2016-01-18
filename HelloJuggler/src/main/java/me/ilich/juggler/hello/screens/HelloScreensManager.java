package me.ilich.juggler.hello.screens;

import me.ilich.juggler.activity.JugglerActivity;
import me.ilich.juggler.ScreensManager;

public class HelloScreensManager extends ScreensManager implements
        MainScreen,
        ListScreen,
        WizardOneScreen,
        WizardTwoScreen,
        WizardThreeScreen,
        LoginScreen {

    public static final String STACK_DEFAULT = "default stack";

    public HelloScreensManager(JugglerActivity<HelloScreensManager> activity) {
        super(activity);
    }

    @Override
    public void onFirstScreen() {
        show(MODE.CLEAR, STACK_DEFAULT, MainScreen.class, null);
    }

    public void main() {
        show(MODE.CLEAR, STACK_DEFAULT, MainScreen.class, null);
    }

    @Override
    public void login() {
        show(MODE.ADD, STACK_DEFAULT, LoginScreen.class, null);
    }

    @Override
    public void toolbarExplain() {
        show(MODE.ADD, STACK_DEFAULT, ToolbarExplainScreen.class, null);
    }

    @Override
    public void list() {
        show(MODE.ADD, STACK_DEFAULT, ListScreen.class, null);
    }

    @Override
    public void itemDetails(int id) {
        show(MODE.ADD, STACK_DEFAULT, ItemDetailScreen.class, new ItemDetailScreen.Params(id));
    }

    @Override
    public void wizardOne() {
        show(MODE.ADD, STACK_DEFAULT, WizardOneScreen.class, null);
    }

    @Override
    public void wizardTwo() {
        show(MODE.ADD, STACK_DEFAULT, WizardTwoScreen.class, null);
    }

    @Override
    public void wizardThree() {
        show(MODE.ADD, STACK_DEFAULT, WizardThreeScreen.class, null);
    }

}
