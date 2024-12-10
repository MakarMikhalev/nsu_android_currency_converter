package ru.nsu.mmikhalev.task3.client

import retrofit2.Call
import retrofit2.http.GET
import ru.nsu.mmikhalev.task3.dto.CurrencyDTO

interface CurrencyApiService {
    @GET("scripts/XML_daily.asp")
    fun getLatestRates(): Call<CurrencyDTO>
}