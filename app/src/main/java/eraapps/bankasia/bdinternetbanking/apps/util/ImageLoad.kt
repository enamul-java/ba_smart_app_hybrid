package eraapps.bankasia.bdinternetbanking.apps.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import eraapps.bankasia.bdinternetbanking.apps.R

fun getProgressDrawble(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadOperatorsImages(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.icon_image_not_found)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.skipMemoryCache(true)
        .into(this)
}

fun ImageView.loadProductImages(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.product)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.skipMemoryCache(true)
        .into(this)
}

fun ImageView.loadOperatorsImagesClear(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.icon_image_not_found)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}

fun ImageView.loadCurrencyImagesClear(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.icon_image_not_found)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}