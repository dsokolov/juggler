package me.ilich.juggler.staticjuggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.ilich.juggler.usage.R;

public class ListFragment extends Fragment {

    public static ListFragment create() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.textView.setText(position + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Juggler.on(ListFragment.this).state(States.details(getContext(), position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return 50;
        }

    }

}
