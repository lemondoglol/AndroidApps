package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = " https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * Create a retrofit object
 * */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit
    .Builder()
    // The converter tells Retrofit what do with the data it gets back from the web service.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


/**
 * When the getProperties() method is invoked,
 * Retrofit appends the endpoint realestate to the base URL
 *
 * The Deferred interface defines a coroutine job that returns a result value
 * (Deferred inherits from Job). The Deferred interface includes a method called await(),
 * which causes your code to wait without blocking until the value is ready,
 * and then that value is returned.
 * */
interface MarsApiService {
    @GET("realestate")   //Endpoint
    fun getProperties():
            Deferred<List<MarsProperty>>
}

object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}