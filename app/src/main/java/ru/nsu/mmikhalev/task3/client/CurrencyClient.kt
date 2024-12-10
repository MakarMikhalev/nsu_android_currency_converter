package ru.nsu.mmikhalev.task3.client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

import ru.nsu.mmikhalev.task3.dto.CurrencyDTO
import ru.nsu.mmikhalev.task3.dto.CurrencyValueDTO

class CurrencyClient {
    companion object {
        suspend fun getAll(): List<CurrencyValueDTO> {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()

            val apiService = retrofit.create(CurrencyApiService::class.java)

            val response: Response<CurrencyDTO> = withContext(Dispatchers.IO) {
                apiService.getLatestRates().execute()
            }

            return if (response.isSuccessful) {
                val currencyDTO = response.body()
                currencyDTO?.currencies ?: emptyList()
            } else {
                println("Error: ${response.message()}")
                emptyList()
            }
        }
    }
}