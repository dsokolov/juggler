package me.ilich.juggler.staticjuggler

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

class FragmentTools(val fragment: Fragment) {

    fun toolbar(view: View, toolbarId: Int) {
        val toolbar = view.findViewById(toolbarId) as Toolbar
        (fragment.activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

}