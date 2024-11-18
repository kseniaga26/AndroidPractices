package ru.kseniaga.androidpractices.data.model

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PersonResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("enName") val enName: String?,
    @SerializedName("profession") val profession: String?,
    @SerializedName("enProfession") val enProfession: String?)
