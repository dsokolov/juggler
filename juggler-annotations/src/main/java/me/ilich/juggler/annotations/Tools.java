package me.ilich.juggler.annotations;

final class Tools {

    static String getJugglerClassName(String s) {
        return "_Juggler_" + s;
    }

    public static <T> String getJugglerFullClassName(Class<T> cls) {
        return cls.getPackage().getName() + "._Juggler_" + cls.getSimpleName();
    }

}
