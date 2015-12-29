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
        showClearStack(MainScreen.class);
    }

    @Override
    public void login() {
        showAddStack(LoginScreen.class);
    }

    @Override
    public void toolbarExplain() {
        showAddStack(ToolbarExplainScreen.class);
    }

    @Override
    public void list() {
        showAddStack(ListScreen.class);
    }

    @Override
    public void itemDetails(int id) {
        showAddStack(ItemDetailScreen.class, new ItemDetailScreen.Params(id));
    }

    @Override
    public void wizardOne() {
        showAddStack(WizardOneScreen.class);
    }

    @Override
    public void wizardTwo() {
        showAddStack(WizardTwoScreen.class);
    }

    @Override
    public void wizardThree() {
        showAddStack(WizardThreeScreen.class);
    }

    @Override
    public void about() {
        showAddStack(AboutScreen.class);
    }

}
