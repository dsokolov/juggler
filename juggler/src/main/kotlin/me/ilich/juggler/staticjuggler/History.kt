package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.transitions.Transition
import java.util.*

object History {

    private val history = mutableMapOf<Int, Stack<Transition>>()

    fun set(activity: AppCompatActivity, stack: Stack<Transition>) {
        val id = activity.ident()
        history.put(id, stack)
    }


    fun get(activity: AppCompatActivity): Stack<Transition>? {
        val id = activity.ident()
        return history.get(id)
    }

    fun push(activity: AppCompatActivity, transition: Transition) {
        val id = activity.ident()
        val stack = history.getOrPut(id, {
            Stack<Transition>()
        });
        stack.push(transition)
    }

    fun peek(activity: AppCompatActivity): Transition? {
        val id = activity.ident()
        val stack = history[id]
        if (stack == null) {
            return null
        } else if (stack.isEmpty()) {
            return null
        } else {
            return stack.peek()
        }
    }

    fun pop(activity: AppCompatActivity): Transition? {
        val id = activity.ident()
        val stack = history[id]
        return stack?.pop()
    }

    fun remove(activity: AppCompatActivity) {
        val id = activity.ident()
        history.remove(id)
    }

    private fun Activity.ident(): Int {
        for (i in Activity::class.java.declaredFields) {
            System.out.println(i)
        }
        val identField = Activity::class.java.getDeclaredField("mIdent")
        identField.isAccessible = true
        val id = identField.getInt(this@ident)
        return id
    }

}