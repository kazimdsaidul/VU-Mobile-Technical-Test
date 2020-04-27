package com.vu.mobile.data.model

import com.google.gson.annotations.SerializedName

class Ad {
    @SerializedName("company")
    var company: String? = null
    @SerializedName("text")
    var text: String? = null
    @SerializedName("url")
    var url: String? = null

}