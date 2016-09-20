package me.ilich.juggler.usage.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.usage.R;

public class StubToolbarFragment extends JugglerToolbarFragment {

    public static StubToolbarFragment create() {
        return new StubToolbarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stub_toolbar, container, false);
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

}
