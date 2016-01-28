package me.ilich.juggler.hello.old.screens;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.hello.old.gui.WizardOneFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_content_below_toolbar)
@JugglerContent(WizardOneFragment.class)
public class WizardOneScreen extends Screen {

    public WizardOneScreen(Navigator_ navigator) {
        super(navigator);
    }

    public void wizardTwo(){

    }

}
