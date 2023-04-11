package dev.mehdi.bakhtiari.presfeed.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("setGlide")
fun setGlide(view: ImageView, imageUrl: String?) = imageUrl?.let { view.loadImageFromGlide(it) }




