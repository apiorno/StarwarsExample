package com.example.starwarsexample.dependecyinjection.modules

import com.example.starwarsexample.ui.MainActivity
import com.example.starwarsexample.adapters.RecyclerViewAdapter
import com.example.starwarsexample.dependecyinjection.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module(includes = [MainActivityContextModule::class])
class AdapterModule {
    @Provides
    @ActivityScope
    fun getStarWarsPeopleLIst(clickListener: RecyclerViewAdapter.ClickListener): RecyclerViewAdapter {
        return RecyclerViewAdapter(clickListener)
    }

    @Provides
    @ActivityScope
    fun getClickListener(mainActivity: MainActivity): RecyclerViewAdapter.ClickListener {
        return mainActivity
    }
}
