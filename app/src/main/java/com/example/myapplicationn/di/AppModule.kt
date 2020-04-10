package com.example.myapplicationn.di

import com.example.myapplicationn.adapter.RepositoryListAdapter
import com.example.myapplicationn.viewModel.SearchListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchListViewModel() }
    single { RepositoryListAdapter(androidContext()) }
}