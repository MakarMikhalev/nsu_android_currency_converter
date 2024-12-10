package ru.nsu.mmikhalev.task3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.nsu.mmikhalev.task3.service.ImageLoaderService
import ru.nsu.mmikhalev.task3.adapter.CurrencyAdapter
import ru.nsu.mmikhalev.task3.databinding.ActivityMainBinding
import ru.nsu.mmikhalev.task3.service.CurrencyService

class ConversionApplication : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imageLoaderService = ImageLoaderService()
    private val currencyService: CurrencyService = CurrencyService(imageLoaderService)
    private val adapter: CurrencyAdapter = CurrencyAdapter(imageLoaderService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        initCurrency()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initCurrency() {
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            currencyService.loadCurrencyList()
            withContext(Dispatchers.Main) {
                adapter.data = currencyService.getCurrencyList()
                adapter.notifyDataSetChanged()

                val countries = currencyService.getCodeCountries()
                Log.d("CodeCountries", "list countries: $countries")

                val fromCurrencyAdapter = ArrayAdapter(
                    this@ConversionApplication,
                    android.R.layout.simple_spinner_item,
                    countries
                )
                fromCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                binding.currencyChange.fromCurrencySpinner.adapter = fromCurrencyAdapter

                val toCurrencyAdapter = ArrayAdapter(
                    this@ConversionApplication,
                    android.R.layout.simple_spinner_item,
                    countries
                )
                toCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.currencyChange.toCurrencySpinner.adapter = toCurrencyAdapter

            }
        }
    }
}
