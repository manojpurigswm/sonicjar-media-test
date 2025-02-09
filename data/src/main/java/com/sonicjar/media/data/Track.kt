package com.sonicjar.media.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("trackID")
    @Expose
    val trackID: Int = 0,
    @SerializedName("trackTitle")
    @Expose
    val trackTitle: String = "",
    @SerializedName("trackSubtitle")
    @Expose
    val trackSubtitle: String = "",
    @SerializedName("trackImageURL")
    @Expose
    val trackImageURL: String = ""
    ) {
}