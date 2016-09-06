package me.ilich.juggler.staticjuggler

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

internal class FragmentJuggler(val fragment: Fragment) : Juggler() {

    override fun onActivity(): AppCompatActivity = fragment.activity as AppCompatActivity

}

