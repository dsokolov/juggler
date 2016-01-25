package me.ilich.juggler;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.states.State;
import me.ilich.juggler.transitions.Transition;

public class Juggler implements Navigable {

    private static Juggler instance;

    public static void init() {
        instance = new Juggler();
    }

    public static Juggler getInstance() {
        if (instance == null) {
            throw new RuntimeException("Call init() first.");
        }
        return instance;
    }

    private List<JugglerActivity> activities = new ArrayList<>();
    private TransactionsRepository transactionsRepository = new TransactionsRepository();
    private Stacks stacks = new Stacks();

    private Juggler() {

    }

    @Override
    public void firstState() {
        TransactionsRepository.Item item = transactionsRepository.getFirst();
        if (item == null) {
            throw new RuntimeException("First state is not registred");
        }
        State state = item.getDestinationInstance();
        Transition transition = item.getTransition();
        doChangeState(transition, state);
    }

    @Override
    public void backState() {
        State currentState = stacks.getCurrentState();
        TransactionsRepository.Item item = transactionsRepository.getBack(currentState);
        if (item == null) {
            throw new RuntimeException("Back state for " + currentState + " is not registred");
        }
        State state = item.getDestinationInstance();
        Transition transition = item.getTransition();
        doChangeState(transition, state);
    }

    @Override
    public boolean upState() {
        State currentState = stacks.getCurrentState();
        TransactionsRepository.Item item = transactionsRepository.getUp(currentState);
        if (item == null) {
            throw new RuntimeException("Up state for " + currentState + " is not registred");
        }
        State state = item.getDestinationInstance();
        Transition transition = item.getTransition();
        doChangeState(transition, state);
        return true;
    }

    @Override
    public void changeState(State state) {
        State currentState = stacks.getCurrentState();
        TransactionsRepository.Item item = transactionsRepository.get(currentState, state, Event.OTHER);
        if (item == null) {
            throw new RuntimeException("No transition from " + currentState + " to " + state + " is not registered");
        }
        Transition transition = item.getTransition();
        doChangeState(transition, state);
    }

    @Override
    public void currentState() {
        JugglerActivity activity = activities.get(activities.size() - 1);
        State currentState = stacks.getCurrentState();
        currentState.process(activity);
    }

    private void doChangeState(Transition transition, State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        transition.execute(activity, this, state);
    }

    public TransactionsRepository getTransactionsRepository() {
        return transactionsRepository;
    }

    void registerActivity(JugglerActivity activity) {
        activities.add(activity);
    }

    void unregisterActivity(JugglerActivity activity) {
        activities.remove(activity);
    }

    public Stacks getStacks() {
        return stacks;
    }

}
