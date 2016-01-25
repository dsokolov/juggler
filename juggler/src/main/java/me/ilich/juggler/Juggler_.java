package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ilich.juggler.fragments.JugglerFragment_;
import me.ilich.juggler.fragments.content.JugglerContentFragment_;
import me.ilich.juggler.fragments.navigation.JugglerNavigationFragment;
import me.ilich.juggler.fragments.toolbar.JugglerToolbarFragment;

@Deprecated
public class Juggler_ {

    private static Juggler_ instance;

    @Deprecated
    public static void init(ScreensManager screensManager) {
        instance = new Juggler_(screensManager);
    }

    public static Juggler_ getInstance() {
        return instance;
    }

    private List<JugglerActivity_> activities = new ArrayList<>();
    private Transition_ startTransition;
    private Screen.Params startParams;
    private Map<Class<? extends Screen>, Screen> screens = new HashMap<>();
    private ScreensManager screensManager;
    private LayoutController layoutController = null;

    private Juggler_(ScreensManager screensManager) {
        this.screensManager = screensManager;
        this.screensManager.setJuggler(this);
        layoutController = new LayoutController();
    }

    public void onCreate(JugglerActivity_ activity, Bundle savedInstanceState) {
        activities.add(activity);
        if (savedInstanceState == null) {
            navigate(activity, startTransition, startParams);
        } else {
            screensManager.onRestore(activity, savedInstanceState);
        }
    }

    public void onDestroy(JugglerActivity_ activity) {
        activities.remove(activity);
    }

    public void setStart(Class<? extends Screen> screen, Screen.Params params) {
        startTransition = new Transition_(null, screen, ScreensManager.MODE.ADD, null);
        startParams = params;
    }

    public void registerScreen(Class<? extends Screen> screenClass) {
        try {
            Constructor<? extends Screen> constructor = screenClass.getDeclaredConstructor(Navigator_.class);
            Screen screen = constructor.newInstance(new Navigator_() {
                @Override
                public void navigate(Transition_ transition, @Nullable Screen.Params params) {
                    Juggler_.this.navigate(transition, params);
                }
            });
            screens.put(screenClass, screen);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("cant register screen", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("cant register screen", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("cant register screen", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("cant register screen", e);
        }
    }

    public void onSaveInstanceState(JugglerActivity_ activity, Bundle outState) {
        screensManager.onSaveInstanceState(activity, outState);
        screensManager.deattachAll(activity);
    }

    public ScreensManager getScreenManager() {
        return screensManager;
    }

    public void setToolbarOptions(@ActionBar.DisplayOptions int options) {
        screensManager.setToolbarOptions(options);
    }

    public int getToolbarOptions() {
        return screensManager.getToolbarOptions();
    }

    public void onAttach(JugglerFragment_ fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarAttached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationAttached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment_) {
            screensManager.onContentAttached((JugglerContentFragment_) fragment);
        }
    }

    public void onDetach(JugglerFragment_ fragment) {
        if (fragment instanceof JugglerToolbarFragment) {
            screensManager.onToolbarDetached((JugglerToolbarFragment) fragment);
        } else if (fragment instanceof JugglerNavigationFragment) {
            screensManager.onNavigationDetached((JugglerNavigationFragment) fragment);
        } else if (fragment instanceof JugglerContentFragment_) {
            screensManager.onContentDetached((JugglerContentFragment_) fragment);
        }
    }

    public void onBeginNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "begin " + screenInstance);
    }

    public void onEndNewScreen(Screen.Instance screenInstance) {
        Log.v("Sokolov", "end " + screenInstance);
    }

    public boolean onBack(JugglerActivity_ activity) {
        boolean b = layoutController.onBackPressed();
        if (!b) {
            b = screensManager.back(activity);
        }
        return b;
    }

    public boolean onUp(JugglerActivity_ activity) {
        return screensManager.up(activity);
    }

    public LayoutController getLayoutController() {
        return layoutController;
    }

    public boolean hasToolbarContainer() {
        return layoutController.getToolbarViewGroup() != null;
    }

    public void setToolbarTitle(CharSequence title, int color) {
        if (screensManager.getToolbarFragment() != null) {
            screensManager.getToolbarFragment().setTitle(title, color);
        }
    }

    @Deprecated
    public void navigate(JugglerActivity_ activity, Transition_ transition, Screen.Params params) {
        Screen.Instance instance = screensManager.getCurrentScreen();
        if (instance == null) {
            transition.execute(activity, screensManager, params);
        } else {
            String currentScreen = instance.getScreenClassName();
            String sourceScreen = transition.getSource().getName();
            if (currentScreen.equals(sourceScreen)) {
                transition.execute(activity, screensManager, params);
            } else {
                throw new RuntimeException("");
            }
        }
    }

    public void navigate(Transition_ transition) {
        JugglerActivity_ activity = activities.get(activities.size() - 1);
        Screen.Instance instance = screensManager.getCurrentScreen();
        if (instance == null) {
            transition.execute(activity, screensManager, null);
        } else {
            String currentScreen = instance.getScreenClassName();
            String sourceScreen = transition.getSource().getName();
            if (currentScreen.equals(sourceScreen)) {
                transition.execute(activity, screensManager, null);
            } else {
                throw new RuntimeException("");
            }
        }
    }

    public void navigate(Transition_ transition, Screen.Params params) {
        JugglerActivity_ activity = activities.get(activities.size() - 1);
        Screen.Instance instance = screensManager.getCurrentScreen();
        if (instance == null) {
            transition.execute(activity, screensManager, params);
        } else {
            String currentScreen = instance.getScreenClassName();
            String sourceScreen = transition.getSource().getName();
            if (currentScreen.equals(sourceScreen)) {
                transition.execute(activity, screensManager, params);
            } else {
                throw new RuntimeException("");
            }
        }
    }

    public <S extends Screen> S navigate(Class<S> screenClass) {
        return (S) screens.get(screenClass);
    }

}
