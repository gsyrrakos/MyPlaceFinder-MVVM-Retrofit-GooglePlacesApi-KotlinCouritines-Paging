package com.example.myapplication.retrofit



import com.example.myapplication.data.PlacesStoreClasses

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Placeservice {
    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"


    }

@GET("maps/api/place/textsearch/json?query=restaurants&key=AIzaSyDczO1dBGYCmxDonWrK-WcQe4cAm2jrMx0")
 suspend fun searchRestauramts(



):PlacesStoreClasses

}