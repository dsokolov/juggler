package me.ilich.juggler;

public class Log {

    private static final boolean SHOW_LOG = true;

    public static void v(Class<?> cls, String s) {
        if (SHOW_LOG) {
            android.util.Log.v(Juggler.TAG, cls.getSimpleName() + " " + s);
        }
    }

    public static void v(String s) {
        if (SHOW_LOG) {
            android.util.Log.v(Juggler.TAG, s);
        }
    }

}
