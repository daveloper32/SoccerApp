package com.daveloper.soccerapp.auxiliar.ext_fun

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daveloper.soccerapp.R

fun ImageView.loadImage (
    urlImage: String,
    circleCrop: Boolean = true,
    errorImage: Int = R.drawable.ic_soccer_ball
) {
    if (urlImage.isNotEmpty()) {
        // Si circleCrop es true cargamos la imagen de forma circular
        if (circleCrop) {
            Glide
                .with(this.context)
                .load(urlImage)
                .circleCrop()
                .error(errorImage)
                .into(this)
            // Si circleCrop es false cargamos la imagen de forma normal
        } else {
            Glide
                .with(this.context)
                .load(urlImage)
                .error(errorImage)
                .into(this)
        }
    }
}
