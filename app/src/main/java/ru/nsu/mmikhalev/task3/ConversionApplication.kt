package ru.nsu.mmikhalev.task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import ru.nsu.mmikhalev.task3.service.ImageLoaderService
import ru.nsu.mmikhalev.task3.adapter.CurrencyAdapter
import ru.nsu.mmikhalev.task3.componentui.CurrencyConversionHandler
import ru.nsu.mmikhalev.task3.componentui.CurrencyUIHandler
import ru.nsu.mmikhalev.task3.databinding.ActivityMainBinding
import ru.nsu.mmikhalev.task3.service.CurrencyService

class ConversionApplication : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val imageLoaderService = ImageLoaderService()
    private val currencyService: CurrencyService = CurrencyService(imageLoaderService)
    private val adapter: CurrencyAdapter = CurrencyAdapter(imageLoaderService)
    private lateinit var currencyUIHandler: CurrencyUIHandler

    private lateinit var currencyConverter: CurrencyConversionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currencyUIHandler = CurrencyUIHandler(binding, currencyService, adapter, this)

        currencyUIHandler.setupRecyclerView()
        currencyUIHandler.setupCurrencySpinners()

        currencyConverter = CurrencyConversionHandler(currencyService, binding)
    }
}
