package com.example.myapplicationn.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationn.repositories.GetRepositoriesUseCase
import com.example.myapplicationn.models.RepositoryResponse

class SearchListViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val repositoryList = MutableLiveData<RepositoryResponse>()
    private val filterTextLiveData = MutableLiveData<String>()
    private val errorLiveData = MutableLiveData<String>()


    fun getErrorMessage() = errorLiveData
    fun getRepositoriesList() = repositoryList

    fun setTextAsFilter(text: String) {
        filterTextLiveData.value = text
    }

    fun getRepositories()  {
        if(filterTextLiveData.value.toString().isNotEmpty()) {
            getRepositoriesUseCase(filterTextLiveData.value.toString(), viewModelScope) { result ->
                result.onSuccess { repositoryList.value = it }
                result.onFailure { errorLiveData.value = it.message.toString() }
            }
        }
    }
}