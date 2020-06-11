package com.onefootball.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.onefootball.R

/**For loading the image of the news item*/
@BindingAdapter("newsImage")
fun ImageView.setNewsImage(url: String) {
    this.load(uri = url) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}