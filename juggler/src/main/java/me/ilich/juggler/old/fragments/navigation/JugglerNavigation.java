package me.ilich.juggler.old.fragments.navigation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JugglerNavigation {

    Class<? extends JugglerNavigationFragment> value();

    int menuItem() default 0;

}
