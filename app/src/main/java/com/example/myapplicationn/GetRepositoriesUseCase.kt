package com.example.myapplicationn

import com.example.myapplicationn.model.Repository
import com.example.myapplicationn.model.RepositoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetRepositoriesUseCase(private val repository: ApiRepository) {

    operator fun invoke(
        key: String,
        coroutineScope: CoroutineScope,
        onResult: (Result<RepositoryResponse>) -> Unit
    ) {
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                runCatching { repository.getRepositories(key) }
            }
            onResult(result)
        }
    }
}