package com.example.myapplication

import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchListViewModel() }
    single { RepositoryListAdapter(androidContext()) }
}