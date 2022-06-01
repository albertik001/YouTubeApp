package com.example.youtube.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadWithGlide(url: String) {
    Glide.with(this).load(url).centerCrop().into(this)
}
