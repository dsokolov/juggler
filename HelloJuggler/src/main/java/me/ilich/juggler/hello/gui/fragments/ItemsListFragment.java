package me.ilich.juggler.hello.gui.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.activities.HelloActivity;
import me.ilich.juggler.hello.states.ItemDetailsState;
import me.ilich.juggler.states.State;

public class ItemsListFragment extends JugglerFragment {

    public static ItemsListFragment create() {
        return new ItemsListFragment();
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
        private ImageView imageView;

        public ViewHolder(Context context, ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false));
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(getContext(), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Integer item = items.get(position);
            holder.textView.setText("item " + item);
            /**
             * setup name for animation enter
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.textView.setTransitionName(String.valueOf(item));
                holder.imageView.setTransitionName(item + "image");
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigateTo().state(Add.linear(new ItemDetailsState(item)));
                    State newState = new ItemDetailsState(item);
                    /**
                     * add elements in State, witch need animate
                     */
//                    newState.addSharedElement(holder.textView, String.valueOf(item));
//                    newState.addSharedElement(holder.imageView, item + "image");
                    newState.addActivityOptions(ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            Pair.create((View) holder.textView, String.valueOf(item)),
                            Pair.create((View) holder.imageView, item + "image")).toBundle());
                    navigateTo().state(Add.newActivityForResult(newState, HelloActivity.class, 123));
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    }

}
