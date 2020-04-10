package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoryListAdapter(
    private val context: Context
) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private val repositories = mutableListOf<Repository>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryName: TextView = itemView.findViewById<TextView>(R.id.repoTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false))
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]

        holder.repositoryName.text = repository.name
    }

    fun setRepositories(repositories: List<Repository>) {
        if (repositories.isNotEmpty()) {
            this.repositories.clear()
        }

        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

}