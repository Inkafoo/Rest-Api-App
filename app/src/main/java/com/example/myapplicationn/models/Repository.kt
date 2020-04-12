package com.example.myapplicationn.models

import com.google.gson.annotations.SerializedName

data class Repository (
    val name: String,
    val description: String,
    val language: String,
    val id: Long,
    @SerializedName ("full_name")
    val fullName: String,
    @SerializedName ("stargazers_count")
    val stars: Int
)