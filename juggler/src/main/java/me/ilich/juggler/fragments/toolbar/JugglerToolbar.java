package me.ilich.juggler.fragments.toolbar;

import android.support.v7.app.ActionBar;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JugglerToolbar {

    Class<? extends JugglerToolbarFragment> value();

    @ActionBar.DisplayOptions int options() default ActionBar.DISPLAY_SHOW_TITLE;

}
