package ru.yavshok.app

import android.content.Context
import coil.ImageLoader
import coil.decode.GifDecoder

object ImageLoaderProvider {
    private var override: ImageLoader? = null


    fun create(context: Context): ImageLoader {
        if (override != null) {
            return override!!
        } else {
            return ImageLoader.Builder(context)
                .components {
                    add(GifDecoder.Factory())
                }
                .build()
        }
    }

    fun overrideForTest(context: Context) {
        override = ImageLoader.Builder(context)
            .components { }
            .build()
    }
}