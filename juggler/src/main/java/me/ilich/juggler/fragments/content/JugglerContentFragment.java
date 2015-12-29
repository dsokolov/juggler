package me.ilich.juggler.fragments.content;

import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.Screen;
import me.ilich.juggler.ScreensManager;
import me.ilich.juggler.fragments.JugglerFragment;

public abstract class JugglerContentFragment<SM extends ScreensManager> extends JugglerFragment {

    protected SM getScreenManager() {
        return ((JugglerActivity<SM>) getActivity()).getScreensManager();
    }

    protected <S extends Screen> S navigateTo() {
        return (S) (((JugglerActivity<SM>) getActivity()).getScreensManager());
    }

}
