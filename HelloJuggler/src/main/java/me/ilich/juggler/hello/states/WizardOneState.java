package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.State;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardOneFragment;

public class WizardOneState extends State<State.Params> {

    public WizardOneState() {
        super(Grid.contentBelowToolbar(), null);
    }

    @Override
    public String getTitle() {
        return "Wizard One";
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = new WizardOneFragment();
                break;
            case TOOLBAR:
                StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create();
                standardToolbarFragment.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
                f = standardToolbarFragment;
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
