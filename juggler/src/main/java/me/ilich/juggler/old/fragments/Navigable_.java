package me.ilich.juggler.old.fragments;

import me.ilich.juggler.Screen;

public interface Navigable_ {

    <S extends Screen> S navigateAddStack(Class<S> sClass);

    <S extends Screen> S navigateClearStack(Class<S> sClass);

    <S extends Screen> S navigateDigStack(Class<S> sClass);

}
