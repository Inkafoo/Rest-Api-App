package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val searchListViewModel: SearchListViewModel by inject()
    private val repoListAdapter: RepositoryListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initKoin()
        initRecyclerView()

        searchRepoEditText.doAfterTextChanged { searchListViewModel.setString(it.toString()) }

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
