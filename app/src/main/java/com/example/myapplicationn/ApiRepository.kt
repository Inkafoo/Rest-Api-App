package com.example.myapplicationn

import com.example.myapplicationn.model.Repository
import com.example.myapplicationn.model.RepositoryResponse

class ApiRepository(private val api: GitHubApi) {

    suspend fun getRepositories(key: String): RepositoryResponse {
        return api.getRepository(key)
    }
}