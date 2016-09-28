package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import me.ilich.juggler.annotations.JugglerActivity
import me.ilich.juggler.annotations.JugglerToolbarFragment

object Dispatcher {

    fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.ifSuitable {
            HistoryStacks.restoreOrIntentState(it, savedInstanceState)
        }
    }

    fun onActivityStarted(activity: Activity) {
        activity.ifSuitable {
            HistoryStacks.restoreFragments(it)
        }
    }

    fun onActivityResumed(activity: Activity) {

    }

    fun onActivityPaused(activity: Activity) {

    }

    fun onActivityStopped(activity: Activity) {

    }

    fun onActivityDestroyed(activity: Activity) {
        activity.ifSuitable {
            HistoryStacks.remove(it)
        }
    }

    fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        activity.ifSuitable {
            HistoryStacks.save(it, outState)
        }
    }

    fun onFragmentCreated(fragment: Fragment, savedInstanceState: Bundle?) {
        HistoryStacks.onFragmentCreated(fragment, savedInstanceState)
    }

    fun onFragmentViewCreated(fragment: Fragment, view: View?, savedInstanceState: Bundle?) {
        val annotated = fragment.javaClass.superclass.isAnnotationPresent(JugglerToolbarFragment::class.java)
        if (annotated && view != null) {
            val toolbarId = fragment.javaClass.superclass.getDeclaredAnnotation(JugglerToolbarFragment::class.java).value
            val toolbar = view.findViewById(toolbarId) as android.support.v7.widget.Toolbar
            (fragment.activity as AppCompatActivity).setSupportActionBar(toolbar)
            val actionBar = (fragment.activity as AppCompatActivity).supportActionBar
            HistoryStacks.onFragmentToolbar(fragment, toolbar, actionBar)
        }
    }

    fun onFragmentStarted(fragment: Fragment) {
        HistoryStacks.onFragmentStarted(fragment)
    }

    fun onFragmentResumed(fragment: Fragment) {

    }

    fun onFragmentPaused(fragment: Fragment) {

    }

    fun onFragmentStopped(fragment: Fragment) {

    }

    fun onFragmentDestroyed(fragment: Fragment) {

    }

    fun onFragmentSaveInstanceState(fragment: Fragment, outState: Bundle) {

    }

    private fun Activity.ifSuitable(method: (AppCompatActivity) -> (Unit)) {
        val classSuitable = this is AppCompatActivity
        val annotationPresent = this.javaClass.isAnnotationPresent(JugglerActivity::class.java)
        if (classSuitable && annotationPresent) {
            method(this as AppCompatActivity)
        }
    }

}