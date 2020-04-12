package com.example.myapplicationn.models

import com.google.gson.annotations.SerializedName

data class Repository (
    val name: String,
    val id: Long,
    val private: Boolean,
    @SerializedName ("full_name")
    val fullName: String,
    @SerializedName ("html_url")
    val url: String,
    @SerializedName ("stargazers_count")
    val stars: Int,
    @SerializedName ("description")
    val description: String

)