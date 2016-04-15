package me.ilich.helloworld;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.Navigable;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.StubContentFragment;
import me.ilich.juggler.hello.gui.TestActivity;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;

public class ToolbarTest extends ActivityInstrumentationTestCase2<TestActivity> {

    public ToolbarTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }

    public void testToolbarText() {
        final Navigable navigable = getActivity().navigateTo();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new ToolbarOnlyState()));
            }
        };
        getInstrumentation().runOnMainSync(runnable);
        getInstrumentation().waitForIdleSync();
        assertEquals(getActivity().getString(R.string.title_toolbar_only), getActivity().getTitle());
    }

    public void testRotate(){
        final Navigable navigable = getActivity().navigateTo();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new ToolbarOnlyState()));
            }
        };
        getInstrumentation().runOnMainSync(runnable);
        getInstrumentation().waitForIdleSync();

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getInstrumentation().waitForIdleSync();
        assertEquals(getActivity().getString(R.string.title_toolbar_only), getActivity().getTitle());

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getInstrumentation().waitForIdleSync();
        assertEquals(getActivity().getString(R.string.title_toolbar_only), getActivity().getTitle());
    }

    public static class ToolbarOnlyState extends ContentBelowToolbarState<VoidParams> {

        public ToolbarOnlyState() {
            super(VoidParams.instance());
        }

        @Override
        protected JugglerFragment onCreateContent(VoidParams params) {
            return StubContentFragment.create();
        }

        @Override
        protected JugglerFragment onCreateToolbar(VoidParams params) {
            return StandardToolbarFragment.createTitleBack();
        }

        @Nullable
        @Override
        public String getTitle(Context context, VoidParams params) {
            return context.getString(R.string.title_toolbar_only);
        }

        @Override
        public Drawable getUpNavigationIcon(Context context, VoidParams params) {
            return context.getResources().getDrawable(android.R.drawable.ic_menu_always_landscape_portrait);
        }

    }

}
