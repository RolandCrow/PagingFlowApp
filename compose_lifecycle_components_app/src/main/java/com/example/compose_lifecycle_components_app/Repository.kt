package com.example.compose_lifecycle_components_app

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    @SerializedName("items")
    val repos: List<Repository>
)

data class Repository(
    @SerializedName("id")
    val id:String,
    @SerializedName("full_name")
    val name: String,
    @SerializedName("description")
    val description: String
)