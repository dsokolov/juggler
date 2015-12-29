package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.fragments.content.JugglerContentFragment;
import me.ilich.juggler.hello.R;

public class ExplainToolbarFragment extends JugglerContentFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explain_toolbar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJuggler().setToolbarOptions(0);
            }
        });
        view.findViewById(R.id.display_show_logo).setOnClickListener(new Listener(ActionBar.DISPLAY_USE_LOGO));
        view.findViewById(R.id.display_show_home).setOnClickListener(new Listener(ActionBar.DISPLAY_SHOW_HOME));
        view.findViewById(R.id.display_show_home_as_up).setOnClickListener(new Listener(ActionBar.DISPLAY_HOME_AS_UP));
        view.findViewById(R.id.display_show_title).setOnClickListener(new Listener(ActionBar.DISPLAY_SHOW_TITLE));
        view.findViewById(R.id.display_show_custom).setOnClickListener(new Listener(ActionBar.DISPLAY_SHOW_CUSTOM));
    }

    private class Listener implements View.OnClickListener {

        private final int val;

        private Listener(int val) {
            this.val = val;
        }

        @Override
        public void onClick(View v) {
            int options = getJuggler().getToolbarOptions();
            boolean b = (options & val) == val;
            if (b) {
                int o = options ^ val;
                getJuggler().setToolbarOptions(o);
            } else {
                int o = options | val;
                getJuggler().setToolbarOptions(o);
            }
        }
    }

}
