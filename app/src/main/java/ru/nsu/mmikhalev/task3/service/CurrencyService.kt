package ru.nsu.mmikhalev.task3.service

import ru.nsu.mmikhalev.task3.client.CurrencyClient
import ru.nsu.mmikhalev.task3.model.Currency
import java.util.Objects

class CurrencyService(private val imageLoaderService: ImageLoaderService) {
    private var currencyMap: Map<String?, Currency> = emptyMap()

    suspend fun loadCurrencyList() {
        val currencyListDTO = CurrencyClient.getAll()
        currencyMap = currencyListDTO.associate { dto ->
            dto.charCode to Currency(
                name = dto.name,
                charCode = dto.charCode,
                value = dto.value,
                imageId = imageLoaderService.getImageByKey(dto.charCode)
            )
        }
    }

    fun getCurrencyList(): List<Currency> = currencyMap.values.toList()

    fun getCodeCountries(): List<String> {
        return currencyMap.keys.filterNotNull()
    }

    fun getCurrencyByName(name: String): Currency? = currencyMap[name]

    fun convertCurrency(fromCurrencyCode: String, toCurrencyName: String, amount: Double): Double? {
        val fromCurrency = getCurrencyByName(fromCurrencyCode)
        val toCurrency = getCurrencyByName(toCurrencyName)

        if (fromCurrency != null && toCurrency != null) {
            val fromValue = fromCurrency.value?.toDoubleOrNull()
            val toValue = toCurrency.value?.toDoubleOrNull()

            if (fromValue != null && toValue != null) {
                return (amount * toValue) / fromValue
            }
        }
        return null
    }
}
