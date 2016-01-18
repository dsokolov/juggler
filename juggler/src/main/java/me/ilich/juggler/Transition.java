package me.ilich.juggler;

public class Transition {

    private final Class<? extends Screen> source;
    private final Class<? extends Screen> destination;
    private final ScreensManager.MODE mode;
    private final String stack;
    private final Screen.Params params;

    public Transition(Class<? extends Screen> source, Class<? extends Screen> destination, ScreensManager.MODE mode, String stack, Screen.Params params) {
        this.source = source;
        this.destination = destination;
        this.stack = stack;
        this.mode = mode;
        this.params = params;
    }

    <SM extends ScreensManager> void execute(SM screensManager) {
        screensManager.show(mode, stack, destination, params);
    }

    public Class<? extends Screen> getSource() {
        return source;
    }

}
