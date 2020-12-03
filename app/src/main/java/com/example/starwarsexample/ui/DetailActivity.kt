package com.example.starwarsexample.ui


import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.starwarsexample.MyApplication
import com.example.starwarsexample.R
import com.example.starwarsexample.dependecyinjection.components.ApplicationComponent
import com.example.starwarsexample.dependecyinjection.components.DaggerDetailActivityComponent
import com.example.starwarsexample.dependecyinjection.components.DetailActivityComponent
import com.example.starwarsexample.dependecyinjection.qualifiers.ApplicationContext
import com.example.starwarsexample.pojos.Film
import com.example.starwarsexample.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    lateinit var detailActivityComponent: DetailActivityComponent

    @Inject
    lateinit var apiInterface: @JvmSuppressWildcards APIInterface

    @Inject
    @field:ApplicationContext
    lateinit var mContext: @JvmSuppressWildcards Context

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        textView = findViewById(R.id.textView)
        val url = intent.getStringExtra("url")
        val applicationComponent: ApplicationComponent =
            MyApplication.get(this).applicationComponent
        detailActivityComponent = DaggerDetailActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        detailActivityComponent.inject(this)
        apiInterface.getFilmData(url, "json")!!.enqueue(object : Callback<Film?> {
            override fun onResponse(call: Call<Film?>?, response: Response<Film?>) {
                val films: Film = response.body()!!
                val text = """
                 Film name:
                 ${films.title}
                 Director:
                 ${films.director}
                 """.trimIndent()
                textView.text = text
            }

            override fun onFailure(call: Call<Film?>?, t: Throwable?) {}
        })
    }
}
