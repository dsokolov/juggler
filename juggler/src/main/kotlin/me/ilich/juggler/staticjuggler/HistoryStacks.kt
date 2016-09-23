package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import me.ilich.juggler.staticjuggler.transitions.Transition
import java.io.Serializable
import java.util.*

object HistoryStacks {

    private const val STATE_HISTORY = "juggler_history"

    private val history = mutableMapOf<Int, Item>()

    fun restore(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            @Suppress("UNCHECKED_CAST")
            val stack = savedInstanceState.getSerializable(STATE_HISTORY) as Stack<Transition>?
            if (stack != null) {
                val id = activity.ident()
                val item = history.getOrPut(id) {
                    Item()
                }
                item.stack.clear()
                item.stack.addAll(stack)
                item.restored = true
                val transition = item.stack.peek()
                transition?.restore(activity)
            }
        }
    }

    fun onActivityStart(activity: AppCompatActivity) {
        val transition = HistoryStacks.peek(activity)
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
        outState.putSerializable(STATE_HISTORY, HistoryStacks.get(activity));
    }

    fun onFragmentCreated(fragment: Fragment, savedInstanceState: Bundle?) {
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

    fun onFragmentToolbar(fragment: Fragment, toolbar: Toolbar, actionBar: android.support.v7.app.ActionBar?) {
        val activity = fragment.activity
        val id = activity.ident()
        val item = history[id]
        if (item != null) {
            item.stack.peek().onFragmentToolbar(toolbar, actionBar)
        }
    }

}