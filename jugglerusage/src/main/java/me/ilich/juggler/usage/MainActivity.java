package me.ilich.juggler.usage;

import android.content.Intent;

import me.ilich.juggler.Log;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.fragments.StubStartForResultContentFragment;

public class MainActivity extends JugglerActivity {

    @Override
    protected State createState() {
        String title = "12345";
        int icon = android.R.drawable.ic_menu_help;
        //State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        //State state = StateFactory.contentBelowToolbarState(title, icon, StubToolbarFragment.class, StubContentFragment.class);
        return StateFactory.contentOnlyState(StubStartForResultContentFragment.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("Sokolov", this + " " + requestCode + " " + resultCode + " " + data);
    }

}
