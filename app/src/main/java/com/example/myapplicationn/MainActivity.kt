package com.example.myapplicationn

import com.example.myapplicationn.adapter.RepositoryListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationn.di.appModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.example.myapplicationn.viewModel.SearchListViewModel
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    private val gitHubApi: GitHubApi = GitHubApi.getApi()
    private val searchListViewModel: SearchListViewModel by inject()
    private val repoListAdapter: RepositoryListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initKoin()
        initRecyclerView()
        handleTextDisplaying()


        lifecycleScope.async{
            loadRepositoryList()
        }


    }

    private suspend fun loadRepositoryList() {
        val list = gitHubApi.searchRepository("kotlin").await().list
        repoListAdapter.setRepositories(list)
    }


    private fun handleTextDisplaying() {
        searchRepoEditText.doAfterTextChanged {
            lifecycleScope.launch {
                searchListViewModel.setString(it.toString())
            }
        }

        searchListViewModel.getString().observe(this, Observer {
            displayText.text = it
        })
    }

    private fun initRecyclerView() {
        repoListRecyclerList.adapter = repoListAdapter
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

}
