package me.ilich.juggler.hello.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.InfinityState;

public class TabsFragment extends JugglerFragment {

    public static TabsFragment newInstance() {
        return new TabsFragment();
    }

    private Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter(getChildFragmentManager());
        if (savedInstanceState != null) {
            //adapter.restoreState(savedInstanceState.getParcelable("ADAPTER"), getClass().getClassLoader());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("ADAPTER", adapter.saveState());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        //final Adapter adapter = new Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo().state(Add.deeper(new InfinityState(123)));
            }
        });
    }

    private class Adapter extends FragmentStatePagerAdapter {

        Map<Integer, TabFragment> fragmentMap = new HashMap<>();

        public TabFragment getFragment(int i) {
            return fragmentMap.get(i);
        }

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TabFragment tabFragment = TabFragment.newInstance(position);
            fragmentMap.put(position, tabFragment);
            return tabFragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            fragmentMap.remove(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page " + position;
        }

    }

}
