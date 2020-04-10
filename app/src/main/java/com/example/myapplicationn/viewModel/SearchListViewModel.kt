package com.example.myapplicationn.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationn.GetRepositoriesUseCase
import com.example.myapplicationn.model.Repository

class SearchListViewModel(private val context: Context,  val getRepositoriesUseCase: GetRepositoriesUseCase) : ViewModel() {

    val repositoriesList = MutableLiveData<Collection<Repository>>()
    private val textLiveData = MutableLiveData<String>()

    fun setString(text: String) {
        textLiveData.value = text
    }

    fun getString() : LiveData<String> = textLiveData

    fun getRepositoriesList()  {
        getRepositoriesUseCase(textLiveData.value.toString(), viewModelScope) { result ->
            result.onSuccess { repositoriesList.value = it }
            result.onFailure { showMessage(it.message.toString()) }
        }
    }

    private  fun showMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG ).show()
    }
}