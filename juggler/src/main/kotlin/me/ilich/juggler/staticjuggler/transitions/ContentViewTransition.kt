package me.ilich.juggler.staticjuggler.transitions

import android.support.v7.app.AppCompatActivity

class ContentViewTransition(val newContentId: Int) : Transition() {

    override fun onChange(activity: AppCompatActivity) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(activity: AppCompatActivity) {
        activity.setContentView(newContentId)
    }

    override fun onRestore(activity: AppCompatActivity) {
        activity.setContentView(newContentId)
    }

    override fun onRollback(activity: AppCompatActivity) {

    }

}

