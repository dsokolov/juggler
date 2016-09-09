package me.ilich.juggler.staticjuggler

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.state.State
import me.ilich.juggler.staticjuggler.transitions.StateTransition
import me.ilich.juggler.staticjuggler.transitions.Transition
import java.util.*

abstract class Juggler {

    companion object {

        private const val STATE_HISTORY = "juggler_history"

        @JvmStatic fun on(activity: AppCompatActivity): Juggler {
            return AppCompatActivityJuggler(activity)
        }

        @JvmStatic fun on(fragment: Fragment): Juggler {
            return FragmentJuggler(fragment)
        }

        @JvmStatic fun on(application : Application): ApplicationTools {
            return ApplicationTools(application)
        }

        @JvmStatic fun tools(fragment: Fragment): FragmentTools {
            return FragmentTools(fragment = fragment)
        }

        @JvmStatic fun callback(fragment: Fragment): FragmentCallback {
            return FragmentCallback(fragment = fragment)
        }

    }

    protected abstract fun onActivity(): AppCompatActivity

    fun create(bundle: Bundle?, state: State) {
        val activity = onActivity()
        if (bundle == null) {
            val transition = StateTransition(state)
            transition.create(activity)
            History.push(activity, transition)
        } else {
            @Suppress("UNCHECKED_CAST")
            val history = bundle.getSerializable(STATE_HISTORY) as Stack<Transition>
            History.set(activity, history)
            val transition = History.peek(activity)
            transition?.restore(activity)
        }
    }

    fun state(state: State) {
        val activity = onActivity()
        val transition = StateTransition(state)
        transition.change(activity)
        History.push(activity, transition)
    }

    fun save(outState: Bundle) {
        val activity = onActivity()
        outState.putSerializable(STATE_HISTORY, History.get(activity));
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

