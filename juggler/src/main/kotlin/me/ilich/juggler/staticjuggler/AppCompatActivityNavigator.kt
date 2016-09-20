package me.ilich.juggler.staticjuggler

import android.support.v7.app.AppCompatActivity

internal class AppCompatActivityNavigator(val activity: AppCompatActivity) : Navigator() {

    override fun onActivity(): AppCompatActivity = activity

}