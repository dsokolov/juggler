package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.State;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardTwoFragment;

public class WizardTwoState extends State<State.Params> {

    public WizardTwoState() {
        super(Grid.contentBelowToolbar(), null);
    }

    @Override
    public int getTitleRes() {
        return R.string.title_wizard_two;
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = new WizardTwoFragment();
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
