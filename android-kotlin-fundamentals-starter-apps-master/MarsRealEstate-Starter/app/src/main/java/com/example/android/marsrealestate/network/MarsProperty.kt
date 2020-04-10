
package com.example.android.marsrealestate.network

import com.squareup.moshi.Json

data class MarsProperty(
    val id: String,
    val type: String,
    val price: Double,
    // The variable is mapped to the JSON attribute img_src using @Json(name = "img_src").
    @Json(name = "img_src")
    val imgSrcUrl: String
)
