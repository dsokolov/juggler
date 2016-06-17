package me.ilich.juggler.usage;

import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.ContentOnlyState;
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
        return new ContentBelowToolbarState<VoidParams>(VoidParams.instance()) {
            @Override
            protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(content);
            }

            @Override
            protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
                return instanceFragment(toolbar);
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

    @Nullable
    private static JugglerFragment instanceFragment(Class<? extends JugglerFragment> content) {
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
        return f;
    }

}
