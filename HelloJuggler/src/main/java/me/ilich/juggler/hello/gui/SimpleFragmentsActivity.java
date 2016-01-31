package me.ilich.juggler.hello.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(me.ilich.juggler.R.layout.juggler_layout_content_below_toolbar);
        if (savedInstanceState == null) {
            StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create();
            getSupportFragmentManager().
                    beginTransaction().
                    replace(me.ilich.juggler.R.id.container_toolbar, standardToolbarFragment).
                    replace(me.ilich.juggler.R.id.container_content, MainFragment.newInstance()).
                    addToBackStack("A").
                    commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                StandardToolbarFragment standardToolbarFragment1 = StandardToolbarFragment.create();
                standardToolbarFragment1.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(me.ilich.juggler.R.id.container_toolbar, standardToolbarFragment1).
                        replace(me.ilich.juggler.R.id.container_content, AboutFragment.newInstance()).
                        addToBackStack("B").
                        commit();
                break;
            case R.id.item2:
                setContentView(me.ilich.juggler.R.layout.juggler_layout_content_below_toolbar_2);
                StandardToolbarFragment standardToolbarFragment2 = StandardToolbarFragment.create();
                standardToolbarFragment2.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(me.ilich.juggler.R.id.container_toolbar, standardToolbarFragment2).
                        replace(me.ilich.juggler.R.id.container_content, ItemsListFragment.create()).
                        addToBackStack("C").
                        commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
       /* getSupportFragmentManager().
                beginTransaction().
                replace(me.ilich.juggler.R.id.container_toolbar, StandardToolbarFragment.create()).
                replace(me.ilich.juggler.R.id.container_content, MainFragment.newInstance()).
                commit();*/
        getSupportFragmentManager().popBackStack("A", 0);
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        int c = getSupportFragmentManager().getBackStackEntryCount();
        Log.v("Sokolov", "stack = " + c);
        if (c <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
