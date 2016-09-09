package me.ilich.juggler.staticjuggler.transitions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import me.ilich.juggler.Log
import java.io.Serializable

abstract class Transition : Serializable {

    private enum class FragmentState {
        INSTANTIATED,
        STARTED,
        STOPED
    }

    private val fragmentStates = mutableMapOf<Int, FragmentState>()
    @Transient protected var toolbar: Toolbar? = null

    fun create(activity: AppCompatActivity) = onCreate(activity)

    fun change(activity: AppCompatActivity) = onChange(activity)

    fun rollback(activity: AppCompatActivity) = onRollback(activity)

    fun restore(activity: AppCompatActivity) = onRestore(activity)

    fun onFragmentInstantiated(fragment: Fragment) {
        val hash = fragment.hashCode()
        fragmentStates.put(hash, FragmentState.INSTANTIATED)
    }

    fun onFragmentToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
    }

    fun onFragmentStarted(fragment: Fragment) {
        val hash = fragment.hashCode()
        fragmentStates.put(hash, FragmentState.STARTED)
        if (fragmentStates.values.count { it != FragmentState.STARTED } == 0) {
            onAllFragmentsStarted(fragment.activity as AppCompatActivity)
        }
    }

    open fun onAllFragmentsStarted(activity: AppCompatActivity) {
    }

    fun onFragmentStopped(fragment: Fragment) {
        val hash = fragment.hashCode()
        fragmentStates.put(hash, FragmentState.STOPED)
    }

    protected abstract fun onCreate(activity: AppCompatActivity)

    protected abstract fun onChange(activity: AppCompatActivity)

    protected abstract fun onRollback(activity: AppCompatActivity)

    protected abstract fun onRestore(activity: AppCompatActivity)

}