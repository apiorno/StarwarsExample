package com.example.starwarsexample

import android.app.Activity
import android.app.Application
import com.example.starwarsexample.dependecyinjection.components.ApplicationComponent
import com.example.starwarsexample.dependecyinjection.components.DaggerApplicationComponent
import com.example.starwarsexample.dependecyinjection.modules.ContextModule


class MyApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
        applicationComponent.injectApplication(this)
    }

    companion object {
        fun get(activity: Activity): MyApplication {
            return activity.application!! as MyApplication
        }
    }
}