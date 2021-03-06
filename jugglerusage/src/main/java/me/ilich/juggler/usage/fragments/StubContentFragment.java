package me.ilich.juggler.usage.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.usage.R;

public class StubContentFragment extends JugglerFragment {

    public static JugglerFragment create() {
        return new StubContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stub_content, container, false);
    }

    @Override
    public boolean onUpPressed() {
        return true;
    }

}
