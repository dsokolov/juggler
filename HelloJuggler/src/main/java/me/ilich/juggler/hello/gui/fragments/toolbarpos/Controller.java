package me.ilich.juggler.hello.gui.fragments.toolbarpos;

import android.view.View;

import me.ilich.juggler.Navigable;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.states.toolbarpos.ToolbarStateA;
import me.ilich.juggler.hello.states.toolbarpos.ToolbarStateB;
import me.ilich.juggler.hello.states.toolbarpos.ToolbarStateC;

public class Controller {

    public static void assing(final Navigable navigable, View v) {
        View toA = v.findViewById(R.id.to_state_a);
        if (toA != null) {
            toA.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    navigable.state(Remove.all(), Add.deeper(new ToolbarStateA()));
                }
            });
        }
        View toB = v.findViewById(R.id.to_state_b);
        if (toB != null) {
            toB.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    navigable.state(Remove.all(), Add.deeper(new ToolbarStateB()));
                }
            });
        }
        View toC = v.findViewById(R.id.to_state_c);
        if (toC != null) {
            toC.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    navigable.state(Remove.all(), Add.deeper(new ToolbarStateC()));
                }
            });
        }
    }

}
