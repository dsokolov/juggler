package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.annotations.JugglerToolbarFragment;
import me.ilich.juggler.usage.R;

@JugglerToolbarFragment(R.id.toolbar)
public class ToolbarFragment extends Fragment {

    public static ToolbarFragment create() {
        return Juggler.on(ToolbarFragment.class).newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stub_toolbar, container, false);
    }

}
