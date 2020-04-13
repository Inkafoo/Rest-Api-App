package com.example.myapplicationn.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationn.R
import com.example.myapplicationn.helpers.MAIN_ACTIVITY_TAG
import com.example.myapplicationn.models.Repository
import com.example.myapplicationn.models.RepositoryResponse

class RepositoryListAdapter(
    private val context: Context
) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private val repositoryList = mutableListOf<Repository>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoName: TextView = itemView.findViewById(R.id.repoNameTextView)
        val repoDescription: TextView = itemView.findViewById(R.id.repoDescriptionTextView)
        val repoLanguage: TextView = itemView.findViewById(R.id.repoLanguageTextView)
        val repoId: TextView = itemView.findViewById(R.id.repoIdTextView)
        val repoStars: TextView = itemView.findViewById(R.id.repoStarsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(
                R.layout.repository_item,
                parent,
                false))
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repositoryItem = repositoryList[position]

        setRepositoryDetails(repositoryItem, holder)
    }

    private fun setRepositoryDetails(
        repository: Repository,
        holder: ViewHolder
    ) {
        holder.apply {
            repoName.text = repository.name
            repoDescription.text = context.getString(R.string.repository_description, repository.description)
            repoLanguage.text = context.getString(R.string.repository_language, repository.language)
            repoId.text = context.getString(R.string.repository_id, repository.id.toString())
            repoStars.text = context.getString(R.string.repository_stars, repository.stars.toString())
        }
    }

    fun setRepositoryList(repositoryResponse: RepositoryResponse) {

        repositoryList.clear()
        repositoryList.addAll(repositoryResponse.list)
        notifyDataSetChanged()
    }

    fun clearRepositoryList() {
        repositoryList.clear()
        notifyDataSetChanged()
    }

}