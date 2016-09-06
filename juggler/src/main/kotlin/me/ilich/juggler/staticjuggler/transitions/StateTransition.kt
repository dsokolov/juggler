package me.ilich.juggler.staticjuggler.transitions

import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.staticjuggler.State
import java.util.*

class StateTransition(val state: State) : Transition() {

    private val contentViewTransition: ContentViewTransition
    private val titleTransition: TitleTransition
    private val transactionName = "juggler_transaction_${UUID.randomUUID()}"

    init {
        contentViewTransition = ContentViewTransition(state.layoutId)
        titleTransition = TitleTransition(state.title)
    }

    override fun onCreate(activity: AppCompatActivity) {
        contentViewTransition.create(activity)
        titleTransition.create(activity)
        val transaction = activity.supportFragmentManager.beginTransaction()
        replaceFragment(state, transaction)
        transaction.addToBackStack(transactionName)
        transaction.commit()
    }

    override fun onChange(activity: AppCompatActivity) {
        titleTransition.create(activity)
        val transaction = activity.supportFragmentManager.beginTransaction()
        replaceFragment(state, transaction)
        transaction.addToBackStack(transactionName)
        transaction.commit()
    }

    override fun onRestore(activity: AppCompatActivity) {
        contentViewTransition.restore(activity)
        titleTransition.restore(activity)
    }

    override fun onRollback(activity: AppCompatActivity) {
        titleTransition.rollback(activity)
        activity.supportFragmentManager.popBackStack(transactionName, 0)
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

