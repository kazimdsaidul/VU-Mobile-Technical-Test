package com.vu.mobile.data.model

import com.google.gson.annotations.SerializedName

class Image {
    @SerializedName("avatar")
    var avatar: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("last_name")
    var lastName: String? = null

}