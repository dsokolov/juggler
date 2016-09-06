package me.ilich.juggler.staticjuggler.transitions

import android.support.v7.app.AppCompatActivity

class TitleTransition(
        val newTitle: String
) : Transition() {

    override fun onChange(activity: AppCompatActivity) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRollback(activity: AppCompatActivity) {
        activity.title = newTitle
    }

    override fun onRestore(activity: AppCompatActivity) {
        activity.title = newTitle
    }

    override fun onCreate(activity: AppCompatActivity) {
        activity.title = newTitle
    }

}

