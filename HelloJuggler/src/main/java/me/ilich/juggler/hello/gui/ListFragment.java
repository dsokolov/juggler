package me.ilich.juggler.hello.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.JugglerContentFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.screens.HelloScreensManager;

public class ListFragment extends JugglerContentFragment<HelloScreensManager> {

    public static ListFragment create() {
        return new ListFragment();
    }

    private List<Integer> items = new ArrayList<Integer>() {
        {
            for (int i = 0; i < 100; i++) {
                add(i);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(Context context, ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false));
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(getContext(), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Integer item = items.get(position);
            holder.textView.setText("item " + item);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getJuggler().getScreenManager().itemDetails(item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    }

}
