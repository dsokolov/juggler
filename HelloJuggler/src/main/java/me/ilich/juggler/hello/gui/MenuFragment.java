package me.ilich.juggler.hello.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.screens.HelloScreensManager;

public class MenuFragment extends JugglerNavigationFragment<HelloScreensManager> {

    private RecyclerView recyclerView;
    private Adapter adapter = new Adapter();
    private CheckedTextView headerCheckedTextView;
    private CheckedTextView footerCheckedTextView;
    private String[] menuItems = new String[]{
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H"
    };
    private int seletedItem = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        headerCheckedTextView = (CheckedTextView) view.findViewById(R.id.text_header);
        footerCheckedTextView = (CheckedTextView) view.findViewById(R.id.text_footer);
    }

    @Override
    protected void onSetSelectedItem(int itemId) {
        seletedItem = itemId;
        adapter.notifyDataSetChanged();
        headerCheckedTextView.setChecked(seletedItem == -1);
        footerCheckedTextView.setChecked(seletedItem == -2);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView textView;

        public ViewHolder(Context context, ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_single_choice, parent, false));
            textView = (CheckedTextView) itemView.findViewById(android.R.id.text1);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(getContext(), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(menuItems[position]);
            holder.textView.setChecked(seletedItem == position);
        }

        @Override
        public int getItemCount() {
            return menuItems.length;
        }

    }

}
