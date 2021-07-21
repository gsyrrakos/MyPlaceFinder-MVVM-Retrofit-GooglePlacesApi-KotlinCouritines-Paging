package com.example.myapplication.ui.restaurant


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myapplication.data.PlaceRepository
import com.example.myapplication.retrofit.Placeservice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RestaurantViewModel @Inject constructor(private val repository:PlaceRepository) :ViewModel() {

    private val currentQuery= MutableLiveData(DEFAULT_QUERY)
    val photos=currentQuery.switchMap { queryString->repository.getSearchResults("-33.8670522,151.1957362").cachedIn(viewModelScope) }

    fun searchRestauramts(query:String){
        currentQuery.value="-33.8670522,151.1957362"


    }
    companion object{

        private const val DEFAULT_QUERY="-33.8670522,151.1957362"
    }

}