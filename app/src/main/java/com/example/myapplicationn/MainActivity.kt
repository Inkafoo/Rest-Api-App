package com.example.myapplicationn

import com.example.myapplicationn.adapters.RepositoryListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.myapplicationn.di.components.Messaging
import com.example.myapplicationn.di.modules.appModule
import com.example.myapplicationn.di.modules.networkModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.myapplicationn.viewModel.SearchListViewModel
import com.example.myapplicationn.helpers.MAIN_ACTIVITY_TAG
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val searchListViewModel: SearchListViewModel by viewModel()
    private val repositoryListAdapter: RepositoryListAdapter by inject()
    private val messaging: Messaging by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initKoin()
        initRecyclerView()

        handlingTextWatcher()

        observeFetchedRepositoryList()
        observeErrorMessages()
    }

    private fun observeFetchedRepositoryList() {
        searchListViewModel.getRepositoriesList().observe(this, Observer {
            it.let {
                repositoryListAdapter.setRepositories(it)
            }
        })
    }

    private fun handlingTextWatcher() {
        searchRepoEditText.doAfterTextChanged {
            if(it.toString().isNotEmpty()) {
                sendRequestForRepositories(it.toString())
            } else {
               repositoryListAdapter.clearList()
            }
        }
    }

    private fun sendRequestForRepositories(filter: String) {
        searchListViewModel.setTextAsFilter(filter.trim())
        searchListViewModel.getRepositories()
        Log.i(MAIN_ACTIVITY_TAG, searchListViewModel.getFilter().value.toString())
    }

    private fun observeErrorMessages() {
        searchListViewModel.getErrorMessage().observe(this, Observer{
            showError(it)
        })
    }

    private fun showError(errorMessage: String) {
        messaging.showErrorToast(errorMessage)
    }

    private fun initRecyclerView() {
        repoListRecyclerList.adapter = repositoryListAdapter
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(appModule, networkModule))
        }
    }

}
