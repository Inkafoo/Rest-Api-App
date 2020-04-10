package com.example.myapplicationn.model

import com.google.gson.annotations.SerializedName

data class Repository (
    val id: Long?,
    val name: String?,
    val description: String?,
    @SerializedName ("full_name")
    val fullName: String?,
    @SerializedName ("html_url")
    val url: String?,
    @SerializedName ("stargazers_count")
    val stars: Long?
)