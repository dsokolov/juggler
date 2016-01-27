package me.ilich.juggler.hello.old.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.old.fragments.JugglerNewInstance;
import me.ilich.juggler.old.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.hello.R;

public class AboutFragment extends JugglerContentFragment_ {

    @JugglerNewInstance
    public static JugglerContentFragment_ create() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
