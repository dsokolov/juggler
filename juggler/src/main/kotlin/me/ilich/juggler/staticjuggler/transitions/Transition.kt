package me.ilich.juggler.staticjuggler.transitions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import me.ilich.juggler.Log
import java.io.Serializable

abstract class Transition : Serializable {

    @Transient protected var toolbar: Toolbar? = null

    fun create(activity: AppCompatActivity) = onCreate(activity)

    fun change(activity: AppCompatActivity) = onChange(activity)

    fun rollback(activity: AppCompatActivity) = onRollback(activity)

    fun restore(activity: AppCompatActivity) = onRestore(activity)

    fun onFragmentToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
    }

    protected abstract fun onCreate(activity: AppCompatActivity)

    protected abstract fun onChange(activity: AppCompatActivity)

    protected abstract fun onRollback(activity: AppCompatActivity)

    protected abstract fun onRestore(activity: AppCompatActivity)

}