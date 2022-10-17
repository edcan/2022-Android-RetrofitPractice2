package kr.edcan.magicconch

import com.squareup.moshi.Json

data class Answer(
    val answer: String,
    @Json(name="image")
    val imageUrl: String
)