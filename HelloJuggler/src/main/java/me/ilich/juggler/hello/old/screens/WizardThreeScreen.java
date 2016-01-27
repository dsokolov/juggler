package me.ilich.juggler.hello.old.screens;

import me.ilich.juggler.old.fragments.Navigator_;
import me.ilich.juggler.Screen;
import me.ilich.juggler.old.fragments.JugglerLayout;
import me.ilich.juggler.old.fragments.content.JugglerContent;
import me.ilich.juggler.hello.old.gui.WizardThreeFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_toolbar)
@JugglerContent(WizardThreeFragment.class)
public class WizardThreeScreen extends Screen {

    public WizardThreeScreen(Navigator_ navigator) {
        super(navigator);
    }

    public void main(){

    }

}
