package com.example.pagingflowapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyContainer {
    val repositoryRetrofitClient: RepositoriesApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.github.com/search/")
        .build().create(RepositoriesApiService::class.java)
}