package dev.mehdi.bakhtiari.presfeed.utils

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.mehdi.bakhtiari.presfeed.BuildConfig
import dev.mehdi.bakhtiari.presfeed.R


fun ImageView.loadImageFromGlide(url: String?) {
    if (url != null) {
        val deviceWidthPixels = Resources.getSystem().displayMetrics.widthPixels
        val deviceHeightPixels = Resources.getSystem().displayMetrics.heightPixels
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .override(deviceWidthPixels, deviceHeightPixels)
            .error(R.drawable.ic_baseline_crop_original_24)
            .into(this)
    }
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this.context, message, duration).show()
}

fun ProgressBar.visibility(state: Boolean) {
    visibility = if (state) View.VISIBLE else View.GONE
}


