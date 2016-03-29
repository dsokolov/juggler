package me.ilich.juggler.states;

public final class VoidParams extends State.Params {

    private static final VoidParams instance = new VoidParams();

    public static VoidParams instance() {
        return instance;
    }

    private VoidParams() {

    }

}
