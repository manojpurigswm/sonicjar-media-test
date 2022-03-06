package com.sonicjar.media.utils

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sonicjar.media.BaseApplication
import com.sonicjar.media.R

class DataBindingUtils {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl", "defaultImage", "notShowDefault"], requireAll = false)
        fun loadImage(view: ImageView, url: String?, @IdRes defaultImage: Int?, notShowDefault: Boolean) {

            val image = if (defaultImage == 0 || defaultImage == null) R.mipmap.ic_launcher
            else defaultImage


            if (notShowDefault) {
                BaseApplication.singleton?.getApplication?.let {
                    Glide.with(it)
                        .load(url)
                        .into(view)
                }
            } else {
                BaseApplication.singleton?.getApplication?.let {
                    Glide.with(it)
                        .load(url)
                        .placeholder(image)
                        .error(image)
                        .into(view)
                }
            }
        }
    }
}