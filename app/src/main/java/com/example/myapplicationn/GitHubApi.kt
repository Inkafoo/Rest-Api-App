package com.example.myapplicationn

import com.example.myapplicationn.model.Repository
import com.example.myapplicationn.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi{

    @GET("search/repositories")
    suspend fun getRepository(@Query("q") query: String) : RepositoryResponse
}