package com.example.myapplicationn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationn.R
import com.example.myapplicationn.models.Repository
import com.example.myapplicationn.models.RepositoryResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class RepositoryListAdapter(
    private val context: Context
) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private val repositories = mutableListOf<Repository>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoName: TextView = itemView.findViewById(R.id.repoNameTextView)
        val repoDescription: TextView = itemView.findViewById(R.id.repoDescriptionTextView)
        val repoLanguage: TextView = itemView.findViewById(R.id.repoLanguageTextView)
        val repoId: TextView = itemView.findViewById(R.id.repoIdTextView)
        val repoStars: TextView = itemView.findViewById(R.id.repoStarsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false))
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]

        holder.apply {
            repoName.text = repository.name
            repoDescription.text = context.getString(R.string.repository_description, repository.description)
            repoLanguage.text = context.getString(R.string.repository_language, repository.language)
            repoId.text = context.getString(R.string.repository_id, repository.id.toString())
            repoStars.text = context.getString(R.string.repository_stars, repository.stars.toString())
        }

    }

    fun setRepositories(repositories: RepositoryResponse) {
        this.repositories.clear()

        this.repositories.addAll(repositories.list)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.repositories.clear()
        notifyDataSetChanged()
    }

}