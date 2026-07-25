package com.example.expensetracker.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.expensetracker.data.remote.responses.ExpenseImage

interface LogoApi {
    @GET("search")
    suspend fun searchLogos(
        @Query("q") merchant: String
    ): ExpenseImage
}
