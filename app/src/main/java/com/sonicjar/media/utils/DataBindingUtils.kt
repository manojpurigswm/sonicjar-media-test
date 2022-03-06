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
        @BindingAdapter("underLineText")
        fun showUnderLineText(view: TextView, text: String?) {
            val content = SpannableString(text)
            content.setSpan(UnderlineSpan(), 0, text?.length ?: 0, 0)
            view.text = content
        }

        @JvmStatic
        @BindingAdapter("background")
        fun showBackground(view: View, id: Int) {
            view.setBackgroundResource(id)
        }



        @JvmStatic
        @BindingAdapter("layout_marginBottom")
        fun setLayoutMarginBottom(view: ViewGroup, marginBottom: Float) {
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = marginBottom.toInt()
            view.layoutParams = params
        }

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