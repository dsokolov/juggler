package me.ilich.juggler.staticjuggler

import android.support.v7.app.AppCompatActivity
import me.ilich.juggler.annotations.JugglerActivity

internal class AppCompatActivityNavigator(val activity: AppCompatActivity) : Navigator() {

    init {
        if (!activity.javaClass.isAnnotationPresent(JugglerActivity::class.java)) {
            throw IllegalArgumentException("activity should have JugglerActivity annotation")
        }
    }

    override fun onActivity(): AppCompatActivity = activity

}