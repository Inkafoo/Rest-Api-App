package com.example.myapplicationn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchListViewModel : ViewModel() {

    private val textLiveData = MutableLiveData<String>()

    fun setString(text: String) {
        textLiveData.value = text
    }

    fun getString() : LiveData<String> {
        return textLiveData
    }


}