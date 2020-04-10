package com.example.myapplicationn.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName ("items")
    val list: Collection<Repository>
)