package ru.nsu.mmikhalev.task3.service

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.nsu.mmikhalev.task3.R

class ImageLoaderService {
    private val roundingRadius = 20;

    private val images: Map<String, Int> = mapOf(
        "AZN" to R.drawable.azn,
        "GBP" to R.drawable.gbp,
        "AMD" to R.drawable.amd,
        "BYN" to R.drawable.byn,
        "BGN" to R.drawable.bgn,
        "BRL" to R.drawable.brl,
        "HUF" to R.drawable.huf,
        "VND" to R.drawable.vnd,
        "HKD" to R.drawable.hkd,
        "GEL" to R.drawable.gel,
        "DKK" to R.drawable.dkk,
        "AED" to R.drawable.aed,
        "EUR" to R.drawable.eur,
        "EGP" to R.drawable.egp,
        "IDR" to R.drawable.idr,
        "KZT" to R.drawable.kzt,
        "CAD" to R.drawable.cad,
        "QAR" to R.drawable.qar,
        "QAR" to R.drawable.qar,
        "KGS" to R.drawable.kgs,
        "USD" to R.drawable.usd,
        "EUR" to R.drawable.eur,
        "INR" to R.drawable.inr,
        "CNY" to R.drawable.cny,
        "MDL" to R.drawable.mdl,
        "NZD" to R.drawable.nzd,
        "PLN" to R.drawable.pln,
        "RON" to R.drawable.ron,
        "XDR" to R.drawable.xdr,
        "SGD" to R.drawable.sgd,
        "TJS" to R.drawable.tjs,
        "THB" to R.drawable.thb,
        "TRY" to R.drawable.turkey,
        "TMT" to R.drawable.tmt,
        "UZS" to R.drawable.uzs,
        "NOK" to R.drawable.nok,
        "AUD" to R.drawable.aud
    )

    fun getImageByKey(key: String?): Int {
        return images[key] ?: R.drawable.default_image
    }

    fun loadRoundedImage(imageView: ImageView, imageUrl: Any) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(roundingRadius)))
            .error(R.drawable.rounded_corners)
            .placeholder(R.drawable.rounded_corners)
            .into(imageView)
    }
}