package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.annotations.JugglerFragment;
import me.ilich.juggler.staticjuggler.state.States;
import me.ilich.juggler.usage.R;

@JugglerFragment
public class MainFragment extends Fragment {

    public static MainFragment create() {
        return Juggler.on(MainFragment.class).newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juggler.on(MainFragment.this).state(States.list(getContext()));
            }
        });
    }

}
