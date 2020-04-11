package com.example.myapplicationn.di.modules

import com.example.myapplicationn.repositories.ApiRepository
import com.example.myapplicationn.repositories.GetRepositoriesUseCase
import com.example.myapplicationn.adapters.RepositoryListAdapter
import com.example.myapplicationn.di.components.Messaging
import com.example.myapplicationn.viewModel.SearchListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RepositoryListAdapter(androidContext()) }
    single { ApiRepository(get()) }
    single { GetRepositoriesUseCase(get()) }
    single { Messaging(androidContext()) }

    viewModel { SearchListViewModel(get()) }

}
