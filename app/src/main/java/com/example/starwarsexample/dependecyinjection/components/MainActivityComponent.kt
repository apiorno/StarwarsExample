package com.example.starwarsexample.dependecyinjection.components

import android.content.Context
import com.example.starwarsexample.ui.MainActivity
import com.example.starwarsexample.dependecyinjection.modules.AdapterModule
import com.example.starwarsexample.dependecyinjection.qualifiers.ActivityContext
import com.example.starwarsexample.dependecyinjection.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [AdapterModule::class], dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    @get:ActivityContext
    val context: Context

    fun injectMainActivity(mainActivity: MainActivity)
}
