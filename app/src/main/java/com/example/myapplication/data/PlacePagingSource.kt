package com.example.myapplication.data

import androidx.paging.PagingSource
import com.example.myapplication.retrofit.Placeservice
import retrofit2.HttpException
import java.io.IOException

class PlacePagingSource(
    private val placeapi: Placeservice, private val query: String


) : PagingSource<Int, PlacesStoreClasses.PlacesResult>() {


    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, PlacesStoreClasses.PlacesResult> {
        val position = params.key ?: 1
        return try {

            val response = placeapi.searchRestauramts()


            val photos =response.results

            PagingSource.LoadResult.Page(
                data = photos,
                prevKey = if (position == 1) null else position - 1,

                nextKey = if (photos.isEmpty()) null else position + 1


            )
        } catch (exception: IOException) {
            PagingSource.LoadResult.Error(exception)

        } catch (exception: HttpException) {
            PagingSource.LoadResult.Error(exception)


        }
    }


}