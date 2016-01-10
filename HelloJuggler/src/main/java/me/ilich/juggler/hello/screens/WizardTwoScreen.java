package me.ilich.juggler.hello.screens;

import me.ilich.juggler.fragments.JugglerLayout;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.Screen;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardTwoFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_toolbar)
@JugglerContent(WizardTwoFragment.class)
public interface WizardTwoScreen extends Screen {

    void wizardThree();

}
