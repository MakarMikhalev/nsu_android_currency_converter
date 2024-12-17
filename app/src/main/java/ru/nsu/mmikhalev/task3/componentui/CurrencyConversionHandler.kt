package ru.nsu.mmikhalev.task3.componentui

import ru.nsu.mmikhalev.task3.databinding.ActivityMainBinding
import ru.nsu.mmikhalev.task3.service.CurrencyService

class CurrencyConversionHandler(
    private val currencyService: CurrencyService,
    private val binding: ActivityMainBinding
) {

    init {
        binding.currencyChange.convertButton.setOnClickListener {
            performConversion()
        }
    }

    private fun performConversion() {
        val amountText = binding.currencyChange.amountEditText.text.toString()
        if (amountText.isBlank()) {
            binding.currencyChange.resultCurrencySpinner.setText("Введите сумму")
            return
        }

        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.currencyChange.resultCurrencySpinner.setText("Некорректная сумма")
            return
        }

        val conversionRate = currencyService.convertCurrency(
            binding.currencyChange.fromCurrencySpinner.selectedItem.toString(),
            binding.currencyChange.toCurrencySpinner.selectedItem.toString(),
            amount
        )
        binding.currencyChange.resultCurrencySpinner.setText(conversionRate?.toString())
    }
}
