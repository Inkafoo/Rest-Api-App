package com.example.myapplicationn.repositories

import com.example.myapplicationn.models.RepositoryResponse
import com.example.myapplicationn.interfaces.GitHubApi

class ApiRepository(private val api: GitHubApi) {

    suspend fun getRepositories(key: String) : RepositoryResponse {
        return api.getRepository(key)
    }
}