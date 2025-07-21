package com.example.topalbumsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.topalbumsapp.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(this)
    }
}
