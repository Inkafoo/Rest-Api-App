package com.example.myapplicationn

import com.example.myapplicationn.model.RepositoryResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi{

    @GET("search/repositories")
    fun searchRepository(@Query("q") query: String) : Deferred<RepositoryResponse>


    companion object  {
        fun getApi(): GitHubApi {
            val url = "https://api.github.com/"

            val interceptor = HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

            return retrofit.create(GitHubApi::class.java)
        }
    }

}