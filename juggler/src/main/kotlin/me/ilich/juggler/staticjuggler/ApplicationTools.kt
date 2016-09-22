package me.ilich.juggler.staticjuggler

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ApplicationTools(
        private val application: Application
) {

    fun init() {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
                Juggler.on(activity as AppCompatActivity).onActivityStart()
            }

            override fun onActivityDestroyed(activity: Activity?) {
                Juggler.on(activity as AppCompatActivity).destroy()
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle) {
                Juggler.on(activity as AppCompatActivity).save(outState)
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                Juggler.on(activity as AppCompatActivity).restore(savedInstanceState)
            }

            override fun onActivityResumed(activity: Activity?) {
            }

        })
    }


}