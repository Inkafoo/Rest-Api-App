package com.example.myapplicationn.di.components

import com.example.myapplicationn.helpers.GITHUB_API_URL
import com.example.myapplicationn.interfaces.GitHubApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    private fun getHttpLoggingInterceptor() : HttpLoggingInterceptor {
       return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private fun getOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .build()
    }

    private fun getRetrofit() : Retrofit {
        return  Retrofit.Builder()
            .baseUrl(GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
    }

    fun getApi() : GitHubApi {
        return getRetrofit().create(GitHubApi::class.java)
    }

}