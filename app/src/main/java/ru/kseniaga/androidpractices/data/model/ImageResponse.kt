package ru.kseniaga.androidpractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ImageResponse(
    val url: String?,
    @SerializedName("previewUrl") val previewUrl: String?
)
