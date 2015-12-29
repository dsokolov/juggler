package me.ilich.juggler;

public interface Navigable {

    <S extends Screen> S navigateAddStack(Class<S> sClass);

    <S extends Screen> S navigateClearStack(Class<S> sClass);

    <S extends Screen> S navigateDigStack(Class<S> sClass);

}
