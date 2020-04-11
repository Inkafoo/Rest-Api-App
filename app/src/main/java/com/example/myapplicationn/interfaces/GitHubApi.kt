package com.example.myapplicationn.interfaces

import com.example.myapplicationn.models.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi{

    @GET("search/repositories")
    suspend fun getRepository(@Query("q") query: String) : RepositoryResponse
}