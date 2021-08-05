package com.example.gotrypper_paging.model

import com.google.gson.annotations.SerializedName

data class News(

    val news: List<New>,
    val status: Boolean
)