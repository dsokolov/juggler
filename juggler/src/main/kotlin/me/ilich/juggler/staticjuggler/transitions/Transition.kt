package me.ilich.juggler.staticjuggler.transitions

import android.support.v7.app.AppCompatActivity
import java.io.Serializable

abstract class Transition : Serializable {

    fun create(activity: AppCompatActivity) {
        onCreate(activity)
    }

    fun change(activity: AppCompatActivity) {
        onChange(activity)
    }

    fun rollback(activity: AppCompatActivity) {
        onRollback(activity)
    }

    fun restore(activity: AppCompatActivity) {
        onRestore(activity)
    }

    protected abstract fun onCreate(activity: AppCompatActivity)

    protected abstract fun onChange(activity: AppCompatActivity)

    protected abstract fun onRollback(activity: AppCompatActivity)

    protected abstract fun onRestore(activity: AppCompatActivity)

}