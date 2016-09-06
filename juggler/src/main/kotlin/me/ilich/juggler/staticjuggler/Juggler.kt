package me.ilich.juggler.staticjuggler

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

class Juggler(
        val activity: AppCompatActivity? = null,
        val fragment: Fragment? = null
) {

    companion object {

        @JvmStatic fun on(activity: AppCompatActivity): Juggler {
            return Juggler(activity = activity)
        }

        @JvmStatic fun on(fragment: Fragment): Juggler {
            return Juggler(fragment = fragment)
        }

        @JvmStatic fun tools(fragment: Fragment): FragmentTools {
            return FragmentTools(fragment = fragment)
        }

    }

    private var bundle: Bundle? = null
    private var stateBuilder: State.Builder? = null
    private var params: State.Params? = null

    fun restore(bundle: Bundle?): Juggler {
        this.bundle = bundle
        return this
    }

    fun state(stateBuilder: State.Builder): Juggler {
        this.stateBuilder = stateBuilder
        return this
    }

    fun params(params: State.Params): Juggler {
        this.params = params
        return this
    }

    fun execute() {
        if (activity == null) {
            if (fragment != null) {
                processState(fragment.activity as AppCompatActivity)
            }
        } else {
            if (bundle == null) {
                if (stateBuilder != null) {
                    processState(activity)
                }
            } else {
                val state = (stateBuilder as State.Builder).build(activity, params)
                processContentView(activity, state)
                processTitle(activity, state)
            }
        }
    }

    private fun processState(activity: AppCompatActivity) {
        val state = (stateBuilder as State.Builder).build(activity, params)
        processContentView(activity, state)
        processTitle(activity, state)
        val transaction = activity.supportFragmentManager.beginTransaction()
        replaceFragment(state, transaction)
        transaction.addToBackStack("123423445")
        transaction.commit()
    }

    private fun processContentView(activity: AppCompatActivity, state: State) {
        activity.setContentView(state.layoutId)
    }

    private fun processTitle(activity: AppCompatActivity, state: State) {
        if (state.title != null) {
            activity.title = state.title
        }
    }

    private fun replaceFragment(state: State, transaction: FragmentTransaction) {
        state.containers.forEach {
            val fragment = state.fragment(it.type)
            if (fragment == null) {

            } else {
                transaction.replace(it.id, fragment)
            }
        }
    }

}