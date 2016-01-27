package me.ilich.juggler.hello.old.screens;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.hello.old.gui.WizardTwoFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_toolbar)
@JugglerContent(WizardTwoFragment.class)
public class WizardTwoScreen extends Screen {

    public WizardTwoScreen(Navigator_ navigator) {
        super(navigator);
    }

    public void wizardThree() {

    }

}
