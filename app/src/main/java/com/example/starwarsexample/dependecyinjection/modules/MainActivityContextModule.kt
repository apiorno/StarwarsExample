package com.example.starwarsexample.dependecyinjection.modules

import android.content.Context
import com.example.starwarsexample.dependecyinjection.qualifiers.ActivityContext
import com.example.starwarsexample.dependecyinjection.scopes.ActivityScope
import com.example.starwarsexample.ui.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class MainActivityContextModule (activity : MainActivity) {

    private var mainActivity: MainActivity = activity

    var context: Context = activity

    @Provides
    @ActivityScope
    fun providesMainActivity(): MainActivity {
        return mainActivity
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return context
    }

}