package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ilich.juggler.usage.R;

public class ContentFragment extends Fragment {

    public static ContentFragment create() {
        return new ContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stub_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Juggler.on(ContentFragment.this).state(MainState.details).execute();
            }
        });
    }

}
