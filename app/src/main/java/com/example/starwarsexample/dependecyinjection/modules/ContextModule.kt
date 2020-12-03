package com.example.starwarsexample.dependecyinjection.modules

import android.content.Context
import com.example.starwarsexample.dependecyinjection.qualifiers.ApplicationContext
import com.example.starwarsexample.dependecyinjection.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }

}