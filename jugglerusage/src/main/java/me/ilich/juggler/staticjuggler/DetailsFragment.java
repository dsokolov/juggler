package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ilich.juggler.usage.R;

public class DetailsFragment extends Fragment {

    public static DetailsFragment create(int id) {
        DetailsFragment f = new DetailsFragment();
        Bundle b = new Bundle();
        b.putInt("id", id);
        f.setArguments(b);
        return f;
    }

    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView idTextView = (TextView) view.findViewById(R.id.id);
        idTextView.setText("id = " + id);
    }

}
