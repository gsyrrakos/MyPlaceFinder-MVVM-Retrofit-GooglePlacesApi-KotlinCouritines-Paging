package com.example.myapplication.di


import com.example.myapplication.retrofit.Placeservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
@Provides
@Singleton
    fun providerRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(Placeservice.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit):Placeservice=
    retrofit.create(Placeservice::class.java)



}