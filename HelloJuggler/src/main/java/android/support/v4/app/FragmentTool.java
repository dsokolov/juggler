package android.support.v4.app;

public class FragmentTool {

    public static void dropIndex(Fragment fragment) {
        fragment.setIndex(-1, fragment);
    }

}
