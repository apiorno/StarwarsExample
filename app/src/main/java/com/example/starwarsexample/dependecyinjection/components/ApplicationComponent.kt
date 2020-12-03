package com.example.starwarsexample.dependecyinjection.components

import android.content.Context
import com.example.starwarsexample.MyApplication
import com.example.starwarsexample.dependecyinjection.modules.ContextModule
import com.example.starwarsexample.dependecyinjection.modules.RetrofitModule
import com.example.starwarsexample.dependecyinjection.qualifiers.ApplicationContext
import com.example.starwarsexample.dependecyinjection.scopes.ApplicationScope
import com.example.starwarsexample.retrofit.APIInterface
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {
    val apiInterface: APIInterface

    @get:ApplicationContext
    val context: Context

    fun injectApplication(myApplication: MyApplication)
}