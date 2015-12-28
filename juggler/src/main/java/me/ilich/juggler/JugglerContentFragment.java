package me.ilich.juggler;

public abstract class JugglerContentFragment<SM extends ScreensManager> extends JugglerFragment {

    protected SM getScreenManager() {
        return ((JugglerActivity<SM>) getActivity()).getScreensManager();
    }

}
