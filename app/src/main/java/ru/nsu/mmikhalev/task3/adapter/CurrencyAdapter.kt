package ru.nsu.mmikhalev.task3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nsu.mmikhalev.task3.service.ImageLoaderService
import ru.nsu.mmikhalev.task3.databinding.ItemCurrencyBinding
import ru.nsu.mmikhalev.task3.model.Currency

class CurrencyAdapter(private val imageLoaderService: ImageLoaderService) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    var data: List<Currency> = listOf()

    companion object {
        const val RUB_CURRENCY = "  ₽"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding, imageLoaderService)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = data[position]
        holder.bind(currency)
    }

    override fun getItemCount(): Int = data.size

    class CurrencyViewHolder(
        private val binding: ItemCurrencyBinding,
        private val imageLoaderService: ImageLoaderService
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(currency: Currency) {
            binding.nameTextView.text = currency.name
            binding.charCodeTextView.text = currency.charCode
            binding.valueTextView.text = currency.value.toString() + RUB_CURRENCY
            imageLoaderService.loadRoundedImage(binding.imageView, currency.imageId)
        }
    }
}