package me.ilich.juggler;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.gui.JugglerActivity;

public class NewActivityTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public NewActivityTestCase() {
        super(JugglerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }

    public void testA() {
        getActivity();
    }

    public void testStub(){

    }

}
