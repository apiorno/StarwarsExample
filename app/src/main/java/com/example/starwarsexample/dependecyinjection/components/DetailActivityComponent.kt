package com.example.starwarsexample.dependecyinjection.components

import com.example.starwarsexample.dependecyinjection.scopes.ActivityScope
import com.example.starwarsexample.ui.DetailActivity
import dagger.Component

@Component(dependencies = [ApplicationComponent::class])
@ActivityScope
interface DetailActivityComponent {
    fun inject(detailActivity: DetailActivity)
}