package com.example.starwarsexample.pojos

import com.google.gson.annotations.SerializedName

class StarWars {
    @SerializedName("results")
    var results: List<People>? = null
}