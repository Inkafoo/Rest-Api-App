package com.example.myapplicationn

import com.example.myapplicationn.model.Repository

class ApiRepository(private val api: GitHubApi) {

    suspend fun getRepositories(key: String): Collection<Repository> {
        return api.getRepository(key)
    }
}