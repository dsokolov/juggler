package me.ilich.juggler;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Stacks implements Serializable {

    private static final String DEFAULT_STACK = "default stack";

    private Map<String, List<Screen.Instance>> stacks = new HashMap<>();
    private String currentStackName = DEFAULT_STACK;

    public Stacks() {
        stacks.put(currentStackName, new ArrayList<Screen.Instance>());
    }

    public boolean isCurrentEmpty() {
        return stacks.get(currentStackName).size() == 0;
    }


    public String getCurrentStackName() {
        return currentStackName;
    }

    public void setCurrentStack(String stackName) {
        if (TextUtils.isEmpty(stackName)) {
            stackName = DEFAULT_STACK;
        }
        if (stacks.containsKey(currentStackName)) {
            if (stacks.get(currentStackName).size() == 0) {
                stacks.remove(currentStackName);
            }
        }
        currentStackName = stackName;
        if (!stacks.containsKey(currentStackName)) {
            stacks.put(currentStackName, new ArrayList<Screen.Instance>());
        }
    }

    public void clearCurrent() {
        stacks.get(currentStackName).clear();
    }

    public void addCurrent(Screen.Instance currentScreenInstance) {
        stacks.get(currentStackName).add(currentScreenInstance);
    }

    public Screen.Instance getCurrentLast() {
        List<Screen.Instance> instances = stacks.get(currentStackName);
        return instances.get(instances.size() - 1);
    }

    public void removeCurrentLast() {
        List<Screen.Instance> instances = stacks.get(currentStackName);
        instances.remove(instances.size() - 1);
    }
}
