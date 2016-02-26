package me.ilich.helloworld;

import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.hello.gui.HelloActivity;

public class ActivityTest extends ActivityInstrumentationTestCase2<HelloActivity> {

    public ActivityTest() {
        super(HelloActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }

    public void testF() {
        int c = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        assertEquals(1, c);
    }

}
