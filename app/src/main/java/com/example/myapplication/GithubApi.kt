package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi{

    @GET("search/repositories")
    fun search(@Query("q") query: String) : Call<RepositoryResponse>

     companion object Factory {
        fun create(): GithubApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(GithubApi::class.java)
        }
    }
}