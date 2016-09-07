package me.ilich.juggler.staticjuggler.transitions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.state.Cell
import me.ilich.juggler.staticjuggler.state.State
import java.util.*

class StateTransition(state: State) : Transition() {

    private val grid = state.grid()
    private val title = state.title()
    private val fragmentFactory: (Cell) -> (Fragment?) = state.fragmentFactory()
    private val transactionName = "juggler_transaction_${UUID.randomUUID()}"

    override fun onCreate(activity: AppCompatActivity) {
        processContentView(activity)
        processTitle(activity)
        val fm = activity.supportFragmentManager
        processFragments(fm)
    }

    override fun onChange(activity: AppCompatActivity) {
        activity.title = title
        val fm = activity.supportFragmentManager
        processFragments(fm)
    }

    override fun onRestore(activity: AppCompatActivity) {
        processContentView(activity)
        activity.title = title
    }

    override fun onRollback(activity: AppCompatActivity) {
        activity.title = title
        activity.supportFragmentManager.popBackStack(transactionName, 0)
    }

    private fun processContentView(activity: AppCompatActivity) = activity.setContentView(grid.layoutId)

    private fun processTitle(activity: AppCompatActivity) {
        activity.title = title
    }

    private fun processFragments(fm: FragmentManager) {
        val transaction = fm.beginTransaction()
        grid?.cells?.forEach {
            val fragment = fragmentFactory(it)
            if (fragment == null) {
                val fragmentForRemove = fm.findFragmentById(it.id)
                transaction.remove(fragmentForRemove)
            } else {
                transaction.replace(it.id, fragment)
            }
        }
        transaction.addToBackStack(transactionName)
        transaction.commit()
    }

}

