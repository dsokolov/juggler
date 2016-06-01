package me.ilich.juggler;

import android.support.annotation.Nullable;

import junit.framework.TestCase;

import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.VoidParams;

public class StateHolderTestCase extends TestCase {

    private Juggler.StateHolder stateHolder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        stateHolder = new Juggler.StateHolder();
    }

    public void testGetSetNull() {
        stateHolder.set(null);
        assertNull(stateHolder.get());
    }

    public void testGetSetVal() {
        State state = new StubState();
        stateHolder.set(state);
        assertEquals(state, stateHolder.get());
    }

    private static class StubState extends State<VoidParams> {

        public StubState() {
            super(Grid.contentBelowToolbar(), VoidParams.instance());
        }

        @Override
        protected JugglerFragment onConvertFragment(int cellType, VoidParams params, @Nullable JugglerFragment fragment) {
            return null;
        }

    }

}
