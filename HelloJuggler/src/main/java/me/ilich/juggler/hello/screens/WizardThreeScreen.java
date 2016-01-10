package me.ilich.juggler.hello.screens;

import me.ilich.juggler.Screen;
import me.ilich.juggler.fragments.JugglerLayout;
import me.ilich.juggler.fragments.content.JugglerContent;
import me.ilich.juggler.fragments.toolbar.JugglerToolbar;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardThreeFragment;

@JugglerLayout(me.ilich.juggler.R.layout.juggler_layout_toolbar)
@JugglerContent(WizardThreeFragment.class)
public interface WizardThreeScreen extends Screen {

    void main();

}
