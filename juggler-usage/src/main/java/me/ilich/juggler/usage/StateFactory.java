package me.ilich.juggler.usage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.ContentToolbarNavigationEndState;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.VoidParams;

public class StateFactory {

    public static ContentOnlyState<VoidParams> contentOnlyState(final Class<? extends JugglerFragment> content) {
        return new ContentOnlyState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }
        };
    }

    public static ContentBelowToolbarState<VoidParams> contentBelowToolbarState(final Class<? extends JugglerFragment> toolbar, final Class<? extends JugglerFragment> content) {
        return contentBelowToolbarState(null, 0, toolbar, content);
    }

    public static ContentBelowToolbarState<VoidParams> contentBelowToolbarState(final String title, final int icon, final Class<? extends JugglerFragment> toolbar, final Class<? extends JugglerFragment> content) {
        return new ContentBelowToolbarState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }

            @Override
            protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(toolbar, JugglerToolbarFragment.addDisplayOptionsToBundle(null, ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP));
            }

            @Nullable
            @Override
            public String getTitle(Context context, VoidParams params) {
                return title;
            }

            @Override
            protected Drawable getUpNavigationIcon(Context context, VoidParams params) {
                return icon == 0 ? null : context.getResources().getDrawable(icon);
            }

        };
    }

    public static ContentToolbarNavigationState<VoidParams> contentToolbarNavigationState(final Class<? extends JugglerFragment> toolbar, final Class<? extends JugglerFragment> navigation, final Class<? extends JugglerFragment> content) {
        return new ContentToolbarNavigationState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }

            @Override
            protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(toolbar);
            }

            @Override
            protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(navigation);
            }
        };
    }

    public static ContentToolbarNavigationEndState<VoidParams> contentToolbarNavigationEndState(final Class<? extends JugglerFragment> toolbar, final Class<? extends JugglerFragment> navigation, final Class<? extends JugglerFragment> content) {
        return new ContentToolbarNavigationEndState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }

            @Override
            protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(toolbar);
            }

            @Override
            protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(navigation);
            }

            @Override
            protected Drawable getUpNavigationIcon(Context context, VoidParams params) {
                return context.getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public int getToolbarDisplayOptions() {
                return ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE;
            }
        };
    }

    public static ContentToolbarNavigationState<VoidParams> contentToolbarNavigationState(final String title, final int icon, final Class<? extends JugglerFragment> toolbar, final Class<? extends JugglerFragment> navigation, final Class<? extends JugglerFragment> content) {
        return new ContentToolbarNavigationState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }

            @Override
            protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(toolbar, JugglerToolbarFragment.addDisplayOptionsToBundle(null, ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP));
            }

            @Override
            protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(navigation);
            }

            @Nullable
            @Override
            public String getTitle(Context context, VoidParams params) {
                return title;
            }

            @Override
            protected Drawable getUpNavigationIcon(Context context, VoidParams params) {
                return context.getResources().getDrawable(icon);
            }
        };
    }

    @Nullable
    private static JugglerFragment instanceFragment(Class<? extends JugglerFragment> content) {
        return instanceFragment(content, null);
    }

    @Nullable
    private static JugglerFragment instanceFragment(Class<? extends JugglerFragment> content, @Nullable Bundle arguments) {
        JugglerFragment f = null;
        if (content != null) {
            try {
                f = content.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (arguments != null && f != null) {
            f.setArguments(arguments);
        }
        return f;
    }

}
