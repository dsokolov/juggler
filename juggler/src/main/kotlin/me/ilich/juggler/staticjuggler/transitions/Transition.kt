package me.ilich.juggler.staticjuggler.transitions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import me.ilich.juggler.staticjuggler.state.Cell
import me.ilich.juggler.staticjuggler.state.State
import java.io.Serializable
import java.util.*

class Transition(state: State, context: Context) : Serializable {

    private enum class FragmentState {
        NULL,
        INSTANTIATED,
        CREATED,
        STARTED,
        STOPPED
    }

    private val grid = state.grid()
    private val title = state.title(context)
    private val icon = state.icon(context)
    private val displayOptions = state.displayOptions()
    private val fragmentFactory: (Cell) -> (Fragment?) = state.fragmentFactory()
    private val transactionName = "juggler_transaction_${UUID.randomUUID()}"


    private val fragmentStates = mutableMapOf<Cell, FragmentState>()
    @Transient protected var toolbar: Toolbar? = null
    @Transient protected var actionBar: ActionBar? = null

    fun create(activity: AppCompatActivity) = onCreate(activity)

    fun change(activity: AppCompatActivity) = onChange(activity)

    fun rollback(activity: AppCompatActivity) = onRollback(activity)

    fun restore(activity: AppCompatActivity) = onRestore(activity)

    fun restoreFragments(activity: AppCompatActivity) = onRestoreFragments(activity)

    fun onFragmentToolbar(toolbar: Toolbar, actionBar: ActionBar?) {
        this.toolbar = toolbar
        this.actionBar = actionBar
    }

    fun onFragmentInstantiated(it: Cell, fragment: Fragment?) {
        if (fragment == null) {
            fragmentStates.put(it, FragmentState.NULL)
        } else {
            fragmentStates.put(it, FragmentState.INSTANTIATED)
        }
    }

    fun onFragmentCreated(fragment: Fragment) {
        val key = grid.cells.find { it.containerId == fragment.id }
        if (key != null) {
            fragmentStates.put(key, FragmentState.CREATED)
            if (fragmentStates.count { it.value == FragmentState.INSTANTIATED } == 0 &&
                    fragmentStates.size == grid.cells.size) {
                onAllFragmentsCreated()
            }
        }
    }

    fun onFragmentStarted(fragment: Fragment) {
        val key = grid.cells.find { it.containerId == fragment.id }
        if (key != null) {
            fragmentStates.put(key, FragmentState.STARTED)
            if (fragmentStates.count { it.value == FragmentState.INSTANTIATED || it.value == FragmentState.CREATED } == 0 &&
                    fragmentStates.size == grid.cells.size) {
                onAllFragmentsStarted()
            }
        }
    }

    fun onCreate(activity: AppCompatActivity) {
        activity.setContentView(grid.layoutId)
        activity.title = title
        val fm = activity.supportFragmentManager
        fragmentStates.clear()
        processFragments(fm)
    }

    fun onChange(activity: AppCompatActivity) {
        activity.title = title
        val fm = activity.supportFragmentManager
        fragmentStates.clear()
        processFragments(fm)
    }

    fun onRestore(activity: AppCompatActivity) {
        activity.setContentView(grid.layoutId)
        activity.title = title
        fragmentStates.clear()
    }

    fun onRestoreFragments(activity: AppCompatActivity) {
        grid.cells.forEach {
            val fragment = activity.supportFragmentManager.findFragmentById(it.containerId)
            onFragmentInstantiated(it, fragment)
        }
    }

    fun onRollback(activity: AppCompatActivity) {
        activity.title = title
        activity.supportFragmentManager.popBackStack(transactionName, 0)
    }

    fun onAllFragmentsCreated() {
        Log.v("Sokolov", "onAllFragmentsCreated")
    }

    fun onAllFragmentsStarted() {
        Log.v("Sokolov", "onAllFragmentsStarted")
        if (icon != null) {
            toolbar?.setNavigationIcon(icon)
        }
        if (displayOptions != null) {
            actionBar?.displayOptions = displayOptions
        }
        toolbar?.setNavigationOnClickListener {
            Log.v("Sokolov", "navigation click")
        }
    }

    private fun processFragments(fm: FragmentManager) {
        val transaction = fm.beginTransaction()
        grid.cells.forEach {
            val fragment = fragmentFactory(it)
            onFragmentInstantiated(it, fragment)
            if (fragment == null) {
                val fragmentForRemove = fm.findFragmentById(it.containerId)
                transaction.remove(fragmentForRemove)
            } else {
                transaction.replace(it.containerId, fragment)
            }
        }
        transaction.addToBackStack(transactionName)
        transaction.commit()
        fm.executePendingTransactions()
    }

}