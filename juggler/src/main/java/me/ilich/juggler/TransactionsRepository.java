package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.states.State;
import me.ilich.juggler.transitions.ClearCurrentStackAndPushTransition;
import me.ilich.juggler.transitions.NewStackPushTransition;
import me.ilich.juggler.transitions.ResetStacksTransition;
import me.ilich.juggler.transitions.Transition;

public class TransactionsRepository {

    private List<Item> items = new ArrayList<>();

    public void registerStartup(State destination, String stackName) {
        Item item = new Item(null, null, destination, new ResetStacksTransition(stackName), Event.STARTUP);
        items.add(item);
    }

    public void registerTransition(Class<? extends State> source, Class<? extends State> destination, Transition transition) {
        Item item = new Item(source, destination, null, transition, Event.OTHER);
        items.add(item);
    }

    public void registerBack(Class<? extends State> source, Transition transition) {
        Item item = new Item(source, null, null, transition, Event.BACK);
        items.add(item);
    }

    public void registerUp(Class<? extends State> source, Transition transition) {
        Item item = new Item(source, null, null, transition, Event.UP);
        items.add(item);
    }

    @Nullable
    public Item getFirst() {
        Item result = null;
        for (Item item : items) {
            if (item.event == Event.STARTUP) {
                result = item;
            }
        }
        return result;
    }

    public Item getBack(State source) {
        Item result = null;
        if (source != null) {
            for (Item item : items) {
                boolean sameSource = source.getClass().equals(item.source);
                boolean isBack = item.event == Event.BACK;
                if (sameSource && isBack) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    public Item getUp(State source) {
        Item result = null;
        if (source != null) {
            for (Item item : items) {
                boolean sameSource = source.getClass().equals(item.source);
                boolean isUp = item.event == Event.UP;
                if (sameSource && isUp) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    public Item get(State source, State destination, Event event) {
        Item result = null;
        for (Item item : items) {
            boolean isSameSource = source.getClass().equals(item.source);
            boolean isSameDestination = destination.getClass().equals(item.destinationClass);
            boolean isSameEvent = event.equals(item.event);
            if (isSameSource && isSameDestination && isSameEvent) {
                result = item;
                break;
            }
        }
        return result;
    }

    public static class Item {

        private final Class<? extends State> source;
        @Nullable
        private final Class<? extends State> destinationClass;
        @Nullable
        private final State destinationInstance;
        private final Transition transition;
        private final Event event;

        private Item(Class<? extends State> source, @Nullable Class<? extends State> destinationClass, @Nullable State destinationInstance, Transition transition, Event event) {
            this.source = source;
            this.destinationClass = destinationClass;
            this.destinationInstance = destinationInstance;
            this.transition = transition;
            this.event = event;
        }

        @Nullable
        public Class<? extends State> getDestinationClass() {
            return destinationClass;
        }

        @Nullable
        public State getDestinationInstance() {
            return destinationInstance;
        }

        public Transition getTransition() {
            return transition;
        }

    }

}
