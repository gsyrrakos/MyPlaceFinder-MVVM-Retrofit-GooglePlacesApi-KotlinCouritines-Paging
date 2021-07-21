package com.example.myapplication.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.myapplication.retrofit.Placeservice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepository @Inject constructor (private val placeapi:Placeservice){

    fun getSearchResults(query:String)=
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,enablePlaceholders = false
            ),pagingSourceFactory = {PlacePagingSource(placeapi,query) }
        ).liveData

}