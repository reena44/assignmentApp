package com.example.assignmenttestapp.model

data class AppData(
    var validDate: String = "",
    var amount: String = "",
    var frequency: String = "",
    var blockMessage: String = "",
    var imageList: ArrayList<Images>? = null,


    )
data class Images(
    val imageId : Int?= null,
    val paymentInfo :String = ""
)
