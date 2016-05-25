package me.ilich.juggler;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;

public class CrossActivity {

    private static final CrossActivity instance = new CrossActivity();

    public static CrossActivity getInstance() {
        return instance;
    }

    private List<Remove.Interface> removes = new ArrayList<>();
    private List<Add.Interface> adds = new ArrayList<>();

    private CrossActivity() {

    }

    public void addRemove(Remove.Interface s) {
        removes.add(s);
    }

    public boolean hasRemove() {
        return removes.size() > 0;
    }

    public Remove.Interface getRemove() {
        Remove.Interface s = removes.get(removes.size() - 1);
        removes.remove(removes.size() - 1);
        return s;
    }

    public void addAdd(Add.Interface s) {
        adds.add(s);
    }

    public boolean hasAdd() {
        return adds.size() > 0;
    }

    public Add.Interface getAdd() {
        Add.Interface s = adds.get(adds.size() - 1);
        adds.remove(adds.size() - 1);
        return s;
    }

}
