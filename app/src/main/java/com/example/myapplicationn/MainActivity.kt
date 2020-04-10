package com.example.myapplicationn

import com.example.myapplicationn.adapter.RepositoryListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.myapplicationn.di.appModule
import com.example.myapplicationn.di.networkModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.myapplicationn.viewModel.SearchListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val searchListViewModel: SearchListViewModel by viewModel()
    private val repoListAdapter: RepositoryListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initKoin()
        initRecyclerView()
        handleTextDisplaying()


        searchListViewModel.repositoriesList.observe(this, Observer { repositories ->
            repositories.let {
                repoListAdapter.setRepositories(repositories)
            }
        })

    }

    private fun handleTextDisplaying() {
        searchRepoEditText.doAfterTextChanged {
                searchListViewModel.setString(it.toString())
                searchListViewModel.getRepositoriesList()
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
            androidContext(this@MainActivity)
            modules(listOf(appModule, networkModule))
        }
    }

}
