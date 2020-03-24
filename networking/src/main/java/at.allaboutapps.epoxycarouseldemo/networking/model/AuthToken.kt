package at.allaboutapps.epoxycarouseldemo.networking.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthToken (
    val accessToken : String,
    val refreshToken : String
)
