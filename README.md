[![Build Status](https://travis-ci.org/dsokolov/juggler.svg?branch=master)](https://travis-ci.org/dsokolov/juggler)
[ ![Download](https://api.bintray.com/packages/dsokolov/maven/juggler/images/download.svg) ](https://bintray.com/dsokolov/maven/juggler/_latestVersion)

# Juggler (0.1.22 with support libs, 0.1.23 with androidx libs)

## What is it?

Juggler is an Android framework represents navigation as a state machine.
You need a Juggler if you want to operate not separated fragments, activities and layouts but combinations of them.

## How to use it?

### Add dependency.

In your root build.gradle add my bintray repository:

``` groovy
allprojects {
    repositories {
        ...
        maven {
            url 'https://dl.bintray.com/dsokolov/maven'
        }
    }
}
```

In your project build.gradle (usually app/build.gralde or mobile/build.gralde):

``` groovy
dependencies {
    ...
    compile 'me.ilich:juggler:0.1.18'
}
```


### Terms

**State** is a combination of layout and its fragments. This is a screen user see and interact with.

**State stack** is a stack of states. The last item in stack is a current screen.

**Transition** is a process on changing state. It includes two phases: 

1. **Transition remove** is a removing some items from state stack (pop);

2. **Transition add** is an adding items to stack (push).


### Usage

First of all, you should create all fragments you need:
 
```java
public class MainFragment extends JugglerFragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    
    ...
    
}
```

```java
public class StandardToolbarFragment extends JugglerToolbarFragment {

    public static StandardToolbarFragment create() {
        StandardToolbarFragment f = new StandardToolbarFragment();
        Bundle b = new Bundle();
        addDisplayOptionsToBundle(b, ActionBar.DISPLAY_SHOW_TITLE);
        f.setArguments(b);
        return f;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }
    
    ...

}

```

```java
public class StandardNavigationFragment extends JugglerNavigationFragment {

    public static StandardNavigationFragment create(int itemId) {
        StandardNavigationFragment f = new StandardNavigationFragment();
        Bundle b = new Bundle();
        addSelectedItemToBundle(b, itemId);
        f.setArguments(b);
        return f;
    }

    ...

}

```

Then link it together by state:

``` java
public class MainState extends ContentToolbarNavigationState<VoidParams> {

    public MainState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, JugglerFragment fragment) {
        return MainFragment.newInstance();
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, JugglerFragment fragment) {
        return StandardToolbarFragment.create();
    }

    @Override
    protected JugglerFragment onConvertNavigation(VoidParams params, JugglerFragment fragment) {
        return StandardNavigationFragment.create(0);
    }

}
```

You have to had only one activity extends `java JugglerActivity`. Probably it should be solile and main activity:

``` java
public class HelloActivity extends JugglerActivity {

    @Override
    protected State createState() {
        return new MainState();
    }
    
}
```

``` xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="me.ilich.juggler.hello">

    <application>

        <activity android:name=".gui.HelloActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>
```

That is all. Now you can use `navigateTo` method of JugglerFragment to replace one or many fragments on current activity.

If you need use `java android.transition` for navigate screens you can use:
1) `addSharedElement(View view, String transitionName)` off your State object if you used Add.deeper
2) or `addActivityOptions(Bundle activityOptions)` off your State object if you used Add.newActivity or Add.newActivityForResult

But if you used second variant you need used `getJugglerActivity().supportStartPostponedEnterTransition()` in your Fragment after setting transitionName your Views.

Complete example you can find in `HelloJuggler` module in this repositiry.