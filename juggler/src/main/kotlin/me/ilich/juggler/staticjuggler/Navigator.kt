package me.ilich.juggler.staticjuggler

import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.state.State
import me.ilich.juggler.staticjuggler.transitions.Transition

abstract class Navigator {

    protected abstract fun onActivity(): AppCompatActivity

    fun firstState(state: State) {
        val activity = onActivity()
        val isRestored = HistoryStacks.isRestored(activity)
        if (!isRestored) {
            val transition = Transition(state, activity)
            HistoryStacks.push(activity, transition)
            transition.create(activity)
        }
    }

    fun state(state: State) {
        val activity = onActivity()
        val currentHistoryItem = HistoryStacks.peek(activity)
        val transition = Transition(state, activity)
        if (currentHistoryItem == null) {
            throw IllegalStateException("No current state. Call firstState(State).")
        }
        currentHistoryItem.allowNext(transition) {
            throw IllegalStateException("Can't change to $transition.")
        }
        HistoryStacks.push(activity, transition)
        transition.change(activity)
    }

    fun backOrFinish() {
        val activity = onActivity()
        HistoryStacks.pop(activity)
        val transition = HistoryStacks.peek(activity)
        if (transition == null) {
            activity.finish()
        } else {
            transition.rollback(activity)
        }
    }

}