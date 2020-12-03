package com.example.starwarsexample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsexample.MyApplication
import com.example.starwarsexample.R
import com.example.starwarsexample.adapters.RecyclerViewAdapter
import com.example.starwarsexample.dependecyinjection.components.ApplicationComponent
import com.example.starwarsexample.dependecyinjection.components.DaggerMainActivityComponent
import com.example.starwarsexample.dependecyinjection.components.MainActivityComponent
import com.example.starwarsexample.dependecyinjection.modules.MainActivityContextModule
import com.example.starwarsexample.dependecyinjection.qualifiers.ActivityContext
import com.example.starwarsexample.dependecyinjection.qualifiers.ApplicationContext
import com.example.starwarsexample.pojos.People
import com.example.starwarsexample.pojos.StarWars
import com.example.starwarsexample.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var mainActivityComponent: MainActivityComponent

    @Inject
    lateinit var recyclerViewAdapter: @JvmSuppressWildcards RecyclerViewAdapter

    @Inject
    lateinit var apiInterface: @JvmSuppressWildcards APIInterface

    @Inject
    @field:ApplicationContext
    lateinit var mContext: @JvmSuppressWildcards Context

    @Inject
    @field:ActivityContext
    lateinit var activityContext: @JvmSuppressWildcards Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val applicationComponent: ApplicationComponent =
            MyApplication.get(this).applicationComponent
        mainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityContextModule(MainActivityContextModule(this))
            .applicationComponent(applicationComponent)
            .build()

        mainActivityComponent.injectMainActivity(this)
        recyclerView.adapter = recyclerViewAdapter

        apiInterface.getPeople("json")!!.enqueue(object : Callback<StarWars?> {
            override fun onResponse(call: Call<StarWars?>?, response: Response<StarWars?>) {
                response.body()?.results?.let { populateRecyclerView(it) }
            }

            override fun onFailure(call: Call<StarWars?>?, t: Throwable?) {}
        })
    }
    private fun populateRecyclerView(response: List<People>) {
        recyclerViewAdapter.setData(response)
    }


    override fun launchIntent(filmName: String?) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show()
        startActivity(Intent(activityContext, DetailActivity::class.java).putExtra("url", filmName))
    }
}