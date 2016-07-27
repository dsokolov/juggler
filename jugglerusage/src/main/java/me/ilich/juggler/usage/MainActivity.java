package me.ilich.juggler.usage;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;

import me.ilich.juggler.Log;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

public class MainActivity extends JugglerActivity {

    @Override
    protected State createState() {
        String title = "12345";
        int icon = android.R.drawable.ic_menu_help;
        return StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        //State state = StateFactory.contentBelowToolbarState(title, icon, StubToolbarFragment.class, StubContentFragment.class);
        //return StateFactory.contentOnlyState(StubStartForResultContentFragment.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("Sokolov", this + " " + requestCode + " " + resultCode + " " + data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_a) {
            getJuggler().openDrawer();
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    getJuggler().closeDrawer();
                }

            }.execute();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
