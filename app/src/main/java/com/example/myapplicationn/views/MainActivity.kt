package com.example.myapplicationn.views

import com.example.myapplicationn.adapters.RepositoryListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.myapplicationn.R
import com.example.myapplicationn.di.components.Messaging
import com.example.myapplicationn.di.modules.appModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.myapplicationn.viewModel.SearchListViewModel
import com.example.myapplicationn.helpers.MAIN_ACTIVITY_TAG
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * RECRUITMENT TASK
 * APP CREATED BY KAROL FURTAK
 * CONTACT: karol.furtak24@gmail.com
 */

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

        observeRepositoryList()
        observeErrorMessages()
    }


    private fun observeRepositoryList() {
        searchListViewModel.getRepositoriesList().observe(this, Observer {
            if(it.list.isNotEmpty()) {
                repositoryListAdapter.setRepositoryList(it)
                Log.i(MAIN_ACTIVITY_TAG, repositoryListAdapter.itemCount.toString())
            } else {
                repositoryListAdapter.clearRepositoryList()
                showMessage("info", getString(R.string.no_results_found))
            }
        })
    }

    private fun handlingTextWatcher() {
        searchRepoEditText.doAfterTextChanged {
            if(it.toString().isNotEmpty()) {
                sendRequestForRepositories(it.toString())
            } else {
               repositoryListAdapter.clearRepositoryList()
            }
        }
    }

    private fun sendRequestForRepositories(filter: String) {
        searchListViewModel.setTextAsFilter(filter.trim())
        searchListViewModel.getRepositories()
    }

    private fun observeErrorMessages() {
        searchListViewModel.getErrorMessage().observe(this, Observer{
            showMessage("error", it)
        })
    }

    private fun showMessage(type: String, message: String) {
        messaging.showToast(type, message)
    }

    private fun initRecyclerView() {
        repoListRecyclerList.adapter = repositoryListAdapter
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

}
