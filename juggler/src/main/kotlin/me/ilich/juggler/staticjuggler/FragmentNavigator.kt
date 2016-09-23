package me.ilich.juggler.staticjuggler

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.annotations.JugglerFragment
import me.ilich.juggler.annotations.JugglerToolbarFragment

internal class FragmentNavigator(val fragment: Fragment) : Navigator() {

    init {
        if (!fragment.javaClass.superclass.isAnnotationPresent(JugglerFragment::class.java) &&
                !fragment.javaClass.superclass.isAnnotationPresent(JugglerToolbarFragment::class.java)) {
            throw IllegalArgumentException("fragment $fragment should have JugglerFragment or JugglerToolbarFragment annotation")
        }
    }

    override fun onActivity(): AppCompatActivity = fragment.activity as AppCompatActivity

}

