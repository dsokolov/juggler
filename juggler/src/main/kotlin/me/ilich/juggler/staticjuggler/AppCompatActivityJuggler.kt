package me.ilich.juggler.staticjuggler

import android.support.v7.app.AppCompatActivity

internal class AppCompatActivityJuggler(val activity: AppCompatActivity) : Juggler() {

    override fun onActivity(): AppCompatActivity = activity

}