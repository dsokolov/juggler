package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.util.Stack;

import me.ilich.juggler.states.State;

public class Stacks {

    private Stack<Group> stateStacks = new Stack<>();

    public void pushToCurrentStack(State state) {
        if (stateStacks.isEmpty()) {
            stateStacks.push(new Group());
        }
        stateStacks.peek().states.push(new Item(state));
    }

    public void pushToNewStack(State state) {
        stateStacks.push(new Group());
        stateStacks.peek().states.push(new Item(state));
    }

    @Nullable
    public State popFromCurrentStack() {
        final State result;
        if (stateStacks.empty()) {
            result = null;
        } else {
            Group currentStack = stateStacks.peek();
            if (currentStack.states.empty()) {
                result = null;
            } else {
                result = currentStack.states.pop().state;
            }
        }
        return result;
    }

    @Nullable
    public State popPrevStack() {
        final State result;
        if (stateStacks.empty()) {
            result = null;
        } else {
            stateStacks.pop();
            if (stateStacks.empty()) {
                result = null;
            } else {
                Group currentStack = stateStacks.peek();
                if (currentStack.states.empty()) {
                    result = null;
                } else {
                    result = currentStack.states.pop().state;
                }
            }
        }
        return result;
    }

    public State peekCurrentStack() {
        final State result;
        if (stateStacks.empty()) {
            result = null;
        } else {
            Group currentStack = stateStacks.peek();
            if (currentStack.states.empty()) {
                result = null;
            } else {
                result = currentStack.states.peek().state;
            }
        }
        return result;
    }

    public State peekPrevStack() {
        final State result;
        if (stateStacks.empty()) {
            result = null;
        } else {
            stateStacks.pop();
            if (stateStacks.empty()) {
                result = null;
            } else {
                Group currentStack = stateStacks.peek();
                if (currentStack.states.empty()) {
                    result = null;
                } else {
                    result = currentStack.states.peek().state;
                }
            }
        }
        return result;
    }

    public void clear() {
        stateStacks.clear();
    }

    private static final class Group {

        private final Stack<Item> states = new Stack<>();

    }

    private static final class Item {

        private final State state;

        private Item(State state) {
            this.state = state;
        }

    }

}
