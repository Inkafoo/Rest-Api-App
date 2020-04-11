package com.example.myapplicationn.models

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName ("items")
    val list: List<Repository>
)