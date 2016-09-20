package me.ilich.juggler.staticjuggler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.state.State
import me.ilich.juggler.staticjuggler.transitions.StateTransition

abstract class Navigator {

    protected abstract fun onActivity(): AppCompatActivity

    fun firstState(state: State) {
        val activity = onActivity()
        val isRestored = History.isRestored(activity)
        if (!isRestored) {
            val transition = StateTransition(state, activity)
            transition.create(activity)
            History.push(activity, transition)
        }
    }

    fun state(state: State) {
        val activity = onActivity()
        val transition = StateTransition(state, activity)
        transition.change(activity)
        History.push(activity, transition)
    }

    fun restore(bundle: Bundle?) {
        val activity = onActivity()
        if (bundle != null) {
            History.restore(activity, bundle)
        }
    }

    fun save(outState: Bundle) {
        val activity = onActivity()
        History.save(activity, outState)
    }

    fun destroy() {
        History.remove(onActivity())
    }

    fun backOrFinish() {
        val activity = onActivity()
        History.pop(activity)
        val transition = History.peek(activity)
        if (transition == null) {
            activity.finish()
        } else {
            transition.rollback(activity)
        }
    }

}