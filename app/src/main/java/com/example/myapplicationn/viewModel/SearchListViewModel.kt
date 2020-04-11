package com.example.myapplicationn.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationn.repositories.GetRepositoriesUseCase
import com.example.myapplicationn.models.RepositoryResponse

class SearchListViewModel(
    val getRepositoriesUseCase: GetRepositoriesUseCase,
    val context: Context
) : ViewModel() {

    private val repositoriesList = MutableLiveData<RepositoryResponse>()
    private val filterTextLiveData = MutableLiveData<String>()
    private val errorLiveData = MutableLiveData<String>()


    fun getErrorMessage() = errorLiveData
    fun getRepositoriesList() = repositoriesList
    fun getFilter() = filterTextLiveData

    fun setTextAsFilter(text: String) {
        filterTextLiveData.value = text
    }

    fun getRepositories()  {
        if(filterTextLiveData.value.toString().isNotEmpty()) {
            getRepositoriesUseCase(filterTextLiveData.value.toString(), viewModelScope) { result ->
                result.onSuccess { repositoriesList.value = it }
                result.onFailure { errorLiveData.value = it.message.toString() }
            }
        }
    }
}