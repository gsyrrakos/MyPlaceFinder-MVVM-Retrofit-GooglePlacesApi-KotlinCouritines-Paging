package com.example.myapplication.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize
@Parcelize
data class PlacesStoreClasses(

    @SerializedName("next_page_token") val nextpagetoken: String,
    @SerializedName("results") val results: List<PlacesResult>


):Parcelable {

    @Parcelize
    data class PlacesResult(
        @SerializedName("name") val name: String,
        @SerializedName("formatted_address") val formatted_address: String,
        @SerializedName("rating") val rating: Double,
        @SerializedName("photos") val photos: List<PlacesPhoto>,
        @SerializedName("user_ratings_total") val user_ratings_total: Int,


        ) : Parcelable {

        @Parcelize
        data class PlacesPhoto(
            @SerializedName("photo_reference") val photo_reference: String,


            ) : Parcelable {}
    }
}











