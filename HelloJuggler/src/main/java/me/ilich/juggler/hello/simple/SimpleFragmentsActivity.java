package me.ilich.juggler.hello.simple;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTool;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import me.ilich.juggler.hello.R;

public class SimpleFragmentsActivity extends AppCompatActivity {

    private final Map<Integer, View> rootViewsMap = new HashMap<>();
    private int currentContentViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            processRooView(R.layout.activity_simple_1);
        } else {
            for (String key : savedInstanceState.keySet()) {
                if (key.startsWith("view_")) {
                    String s = key.substring("view_".length());
                    int id = Integer.parseInt(s);
                    SparseArray<Parcelable> a = savedInstanceState.getSparseParcelableArray(key);
                    View v = LayoutInflater.from(this).inflate(id, null);
                    v.restoreHierarchyState(a);
                    rootViewsMap.put(id, v);
                    /*Fragment f = getSupportFragmentManager().findFragmentById(id);
                    f.resto*/
                } else if (key.startsWith("fragment_")) {
                    String s = key.substring("fragment_".length());
                    int sep = s.indexOf("_");
                    String layoutIdStr = s.substring(0, sep);
                    String resIdStr = s.substring(sep + 1);
                    int layoutId = Integer.parseInt(layoutIdStr);
                    int viewId = Integer.parseInt(resIdStr);
                    Fragment.SavedState savedState = savedInstanceState.getParcelable(key);
                    Fragment fragment = getSupportFragmentManager().findFragmentById(viewId);
                    //fragment.onViewStateRestored(null);
                    //FragmentTool.dropIndex(fragment);
                    //fragment.setInitialSavedState(savedState);
                }
            }
            processRooView(savedInstanceState.getInt("cv"));
        }
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.v("Sokolov", "onBackStackChanged");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cv", currentContentViewId);
        for (int layoutId : rootViewsMap.keySet()) {
            View v = rootViewsMap.get(layoutId);
            SparseArray<Parcelable> a = new SparseArray<>();
            v.saveHierarchyState(a);
            outState.putSparseParcelableArray("view_" + layoutId, a);

            for (int i = 0; i < ((ViewGroup) v).getChildCount(); i++) {
                View cv = ((ViewGroup) v).getChildAt(i);
                Fragment fragment = getSupportFragmentManager().findFragmentById(cv.getId());
                if (fragment != null) {
                    Fragment.SavedState savedState = getSupportFragmentManager().saveFragmentInstanceState(fragment);
                    outState.putParcelable("fragment_" + layoutId + "_" + cv.getId(), savedState);
                }
            }

        }
    }

    private void processRooView(int layoutId) {
        if (rootViewsMap.containsKey(layoutId)) {
            View v = rootViewsMap.get(layoutId);
            setContentView(v);
        } else {
            View v = LayoutInflater.from(this).inflate(layoutId, null);
            rootViewsMap.put(layoutId, v);
            setContentView(v);
        }
        currentContentViewId = layoutId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragments, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                Log.v("Sokolov", "backstack size = " + count);
                for (int i = 0; i < count; i++) {
                    FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(i);
                    Log.v("Sokolov", i + ") " + backStackEntry.getId() + " " + backStackEntry.getName());
                }
                break;
            case R.id.menu_content_view_1:
                processRooView(R.layout.activity_simple_1);
                break;
            case R.id.menu_content_view_2:
                processRooView(R.layout.activity_simple_2);
                break;
            case R.id.menu_content_view_3:
                processRooView(R.layout.activity_simple_3);
                break;
            case R.id.menu_replace_A_to_1:
                Log.v("Sokolov", "replace fragmentA 1 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new FragmentA(), "A").addToBackStack("A_1").commit();
                Log.v("Sokolov", "replace fragmentA 1 end");
                break;
            case R.id.menu_replace_B_to_1:
                Log.v("Sokolov", "replace fragmentB 1 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, new FragmentB(), "B").addToBackStack("B_1").commit();
                Log.v("Sokolov", "replace fragmentB 1 end");
                break;
            case R.id.menu_replace_A_to_2:
                Log.v("Sokolov", "replace fragmentA 2 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new FragmentA(), "A").addToBackStack("A_2").commit();
                Log.v("Sokolov", "replace fragmentA 2 end");
                break;
            case R.id.menu_replace_B_to_2:
                Log.v("Sokolov", "replace fragmentB 2 start");
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new FragmentB(), "B").addToBackStack("B_2").commit();
                Log.v("Sokolov", "replace fragmentB 2 end");
                break;
            case R.id.menu_pop:
                Log.v("Sokolov", "popBackStack start");
                getSupportFragmentManager().popBackStack();
                Log.v("Sokolov", "popBackStack end");
                break;
            case R.id.menu_pop_imm:
                Log.v("Sokolov", "popBackStackImmediate start");
                getSupportFragmentManager().popBackStackImmediate();
                Log.v("Sokolov", "popBackStackImmediate end");
                break;
            case R.id.menu_find_A_replace_to_1:
                Log.v("Sokolov", "find start");
                Fragment f = getSupportFragmentManager().findFragmentByTag("A");
                Log.v("Sokolov", "found " + f);
                getSupportFragmentManager().beginTransaction().detach(f).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, f).commit();
                Log.v("Sokolov", "find end");
                break;
            case R.id.menu_replace_last_1:
                Log.v("Sokolov", "replace last 1 start");
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new FragmentA()).addToBackStack("A").commit();
                Log.v("Sokolov", "replace last 1 end");
                break;
            case R.id.menu_replace_last_2:
                Log.v("Sokolov", "replace last 2 start");
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new FragmentB()).addToBackStack("B").commit();
                Log.v("Sokolov", "replace last 2 end");
                break;
            case R.id.menu_replace_last_3:
                Log.v("Sokolov", "replace last 3 start");
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.container2, new Fragment3()).addToBackStack("C").commit();
                Log.v("Sokolov", "replace last 3 end");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
