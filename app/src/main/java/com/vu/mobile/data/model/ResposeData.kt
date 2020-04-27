package com.vu.mobile.data.model

import com.google.gson.annotations.SerializedName

class ResposeData {
    @SerializedName("ad")
    var ad: Ad? = null
    @SerializedName("data")
    var images: ArrayList<Image> = ArrayList()
    @SerializedName("page")
    var page: Long? = null
    @SerializedName("per_page")
    var perPage: Long? = null
    @SerializedName("total")
    var total: Long? = null
    @SerializedName("total_pages")
    var totalPages: Long? = null

}