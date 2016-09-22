package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import me.ilich.juggler.staticjuggler.transitions.Transition
import java.io.Serializable
import java.util.*

object History {

    private const val STATE_HISTORY = "juggler_history"

    private val history = mutableMapOf<Int, Item>()

    fun restore(activity: AppCompatActivity, bundle: Bundle) {
        @Suppress("UNCHECKED_CAST")
        val stack = bundle.getSerializable(STATE_HISTORY) as Stack<Transition>
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        item.stack.clear()
        item.stack.addAll(stack)
        val transition = History.peek(activity)
        transition?.restore(activity)
        History.setRestored(activity, true)
    }

    fun onActivityStart(activity: AppCompatActivity) {
        val transition = History.peek(activity)
        transition?.restoreFragments(activity)
    }

    fun get(activity: AppCompatActivity): Stack<Transition>? {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        return item.stack
    }

    fun push(activity: AppCompatActivity, transition: Transition) {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        item.stack.push(transition)
    }

    fun peek(activity: AppCompatActivity): Transition? {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        val stack = item.stack
        if (stack.isEmpty()) {
            return null
        } else {
            return stack.peek()
        }
    }

    fun pop(activity: AppCompatActivity): Transition? {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        val stack = item.stack
        return stack.pop()
    }

    fun remove(activity: AppCompatActivity) {
        val id = activity.ident()
        history.remove(id)
    }

    fun setRestored(activity: AppCompatActivity, b: Boolean) {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        item.restored = b
    }

    fun isRestored(activity: AppCompatActivity): Boolean {
        val id = activity.ident()
        val item = history.getOrPut(id) {
            Item()
        }
        return item.restored
    }

    private fun Activity.ident(): Int {
        val identField = Activity::class.java.getDeclaredField("mIdent")
        val lastValue = identField.isAccessible
        identField.isAccessible = true
        val id = identField.getInt(this@ident)
        identField.isAccessible = lastValue
        return id
    }

    class Item : Serializable {

        val stack: Stack<Transition> = Stack()
        var restored: Boolean = false

    }

    fun save(activity: AppCompatActivity, outState: Bundle) {
        outState.putSerializable(STATE_HISTORY, History.get(activity));
    }

    fun onFragmentCreated(fragment: Fragment) {
        val activity = fragment.activity
        val id = activity.ident()
        val item = history[id]
        if (item != null) {
            item.stack.peek().onFragmentCreated(fragment)
        }
    }

    fun onFragmentStarted(fragment: Fragment) {
        val activity = fragment.activity
        val id = activity.ident()
        val item = history[id]
        if (item != null) {
            item.stack.peek().onFragmentStarted(fragment)
        }
    }

    fun onFragmentToolbar(fragment: Fragment, toolbar: Toolbar) {
        val activity = fragment.activity
        val id = activity.ident()
        val item = history[id]
        if (item != null) {
            item.stack.peek().onFragmentToolbar(toolbar)
        }
    }

}