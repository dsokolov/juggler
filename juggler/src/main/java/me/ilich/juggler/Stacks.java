package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ilich.juggler.states.State;

public class Stacks {

    private Map<String, List<State>> stacks = new HashMap<>();
    private List<String> stackNames = new ArrayList<>();

    public State getCurrentState() {
        String currentStackName = stackNames.get(stackNames.size() - 1);
        List<State> currentStack = stacks.get(currentStackName);
        return currentStack.get(currentStack.size() - 1);
    }

    public void setCurrentStack(String stackName) {
        stackNames.add(stackName);
    }

    public void addStateToCurrentStack(State state) {
        String currentStackName = stackNames.get(stackNames.size() - 1);
        List<State> currentStack;
        if (stacks.containsKey(currentStackName)) {
            currentStack = stacks.get(currentStackName);
        } else {
            currentStack = new ArrayList<>();
            stacks.put(currentStackName, currentStack);
        }
        currentStack.add(state);
    }

    public void clearCurrentStack() {
        String currentStackName = stackNames.get(stackNames.size() - 1);
        List<State> currentStack;
        if (stacks.containsKey(currentStackName)) {
            currentStack = stacks.get(currentStackName);
            currentStack.clear();
        } else {
            currentStack = new ArrayList<>();
            stacks.put(currentStackName, currentStack);
        }
    }

    @Nullable
    public State prevState() {
        final State result;
        if (stackNames.size() > 0) {
            String currentStackName = stackNames.get(stackNames.size() - 1);
            if (stacks.containsKey(currentStackName)) {
                List<State> currentStack = stacks.get(currentStackName);
                if (currentStack.size() > 0) {
                    currentStack.remove(currentStack.size() - 1);
                    if (currentStack.size() > 0) {
                        result = currentStack.get(currentStack.size() - 1);
                    } else {
                        stacks.remove(currentStackName);
                        stackNames.remove(stackNames.size() - 1);
                        result = prevState();
                    }
                } else {
                    stacks.remove(currentStackName);
                    stackNames.remove(stackNames.size() - 1);
                    result = prevState();
                }
            } else {
                stackNames.remove(stackNames.size() - 1);
                result = prevState();
            }
        } else {
            result = null;
        }
        return result;
    }

    public void reset() {
        stackNames.clear();
        stacks.clear();
    }

}
