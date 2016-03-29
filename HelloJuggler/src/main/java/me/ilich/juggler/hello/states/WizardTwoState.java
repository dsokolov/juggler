package me.ilich.juggler.hello.states;

import android.content.Context;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardTwoFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.VoidParams;

public class WizardTwoState extends ContentBelowToolbarState<VoidParams> {

    public WizardTwoState() {
        super(VoidParams.instance());
    }

    @Override
    public int getTitleRes(Context context, VoidParams params) {
        return R.string.title_wizard_two;
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return new WizardTwoFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }

}
