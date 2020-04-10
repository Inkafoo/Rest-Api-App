package com.example.myapplicationn.di

import com.example.myapplicationn.ApiRepository
import com.example.myapplicationn.GetRepositoriesUseCase
import com.example.myapplicationn.adapter.RepositoryListAdapter
import com.example.myapplicationn.viewModel.SearchListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RepositoryListAdapter(androidContext()) }
    single { ApiRepository(get()) }
    single { GetRepositoriesUseCase(get()) }

    viewModel { SearchListViewModel(androidContext(), get()) }

}
