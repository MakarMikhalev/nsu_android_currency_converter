package ru.nsu.mmikhalev.task3.componentui

import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.nsu.mmikhalev.task3.R
import ru.nsu.mmikhalev.task3.adapter.CurrencyAdapter
import ru.nsu.mmikhalev.task3.service.CurrencyService
import ru.nsu.mmikhalev.task3.databinding.ActivityMainBinding

class CurrencyUIHandler(
    private val binding: ActivityMainBinding,
    private val currencyService: CurrencyService,
    private val adapter: CurrencyAdapter,
    private val lifecycleOwner: LifecycleOwner
) {

    fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter
    }

    fun setupCurrencySpinners() {
        lifecycleOwner.lifecycleScope.launch {
            currencyService.loadCurrencyList()
            withContext(Dispatchers.Main) {
                adapter.data = currencyService.getCurrencyList()
                adapter.notifyDataSetChanged()

                val countries = currencyService.getCodeCountries()
                if (countries.isNotEmpty()) {
                    val fromCurrencyAdapter = ArrayAdapter(
                        binding.root.context,
                        R.layout.spinner_layout,
                        countries
                    )
                    fromCurrencyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    binding.currencyChange.fromCurrencySpinner.adapter = fromCurrencyAdapter

                    val toCurrencyAdapter = ArrayAdapter(
                        binding.root.context,
                        R.layout.spinner_layout,
                        countries
                    )
                    toCurrencyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
                    binding.currencyChange.toCurrencySpinner.adapter = toCurrencyAdapter
                }
            }
        }
    }
}

