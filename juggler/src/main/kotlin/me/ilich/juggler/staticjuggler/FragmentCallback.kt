package me.ilich.juggler.staticjuggler

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class FragmentCallback(private val fragment: Fragment) {

    fun onToolbar(toolbar: Toolbar) {
        History.peek(fragment.activity as AppCompatActivity)?.onFragmentToolbar(toolbar)
    }

    fun onStart() {
        History.peek(fragment.activity as AppCompatActivity)?.onFragmentStarted(fragment)
    }

    fun onStop() {
        History.peek(fragment.activity as AppCompatActivity)?.onFragmentStopped(fragment)
    }

}