package ru.nsu.mmikhalev.task3.service

import android.util.Log
import ru.nsu.mmikhalev.task3.client.CurrencyClient
import ru.nsu.mmikhalev.task3.model.Currency

class CurrencyService(private val imageLoaderService: ImageLoaderService) {
    private var currencyMap: Map<String?, Currency> = emptyMap()

    suspend fun loadCurrencyList() {
        val currencyListDTO = CurrencyClient.getAll()
        currencyMap = currencyListDTO.associate { dto ->
            dto.charCode to Currency(
                name = dto.name ?: "",
                charCode = dto.charCode ?: "",
                value = dto.value?.replace(",", ".")?.toDoubleOrNull() ?: 0.0,
                imageId = imageLoaderService.getImageByKey(dto.charCode)
            )
        }

        val charRub = "RUB"
        val currency = Currency(charRub, charRub, 1.0, imageLoaderService.getImageByKey("RU"))
        currencyMap = currencyMap.plus(charRub to currency)
    }

    fun getCurrencyList(): List<Currency> = currencyMap.values.toList()

    fun getCodeCountries(): List<String> {
        return currencyMap.keys.filterNotNull()
    }

    fun convertCurrency(fromCurrencyCode: String, toCurrencyName: String, amount: Double): Double? {
        Log.d("Convert currency", "fromCurrencyCode=$fromCurrencyCode, toCurrencyName=$toCurrencyName, amount=$amount")
        val fromCurrency = currencyMap[fromCurrencyCode]
        val toCurrency = currencyMap[toCurrencyName]

        if (fromCurrency?.value != null && toCurrency?.value != null) {
            return (amount * fromCurrency.value) / toCurrency.value
        }
        return null
    }
}