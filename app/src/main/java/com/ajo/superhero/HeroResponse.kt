package com.ajo.superhero

import com.google.gson.annotations.SerializedName

class HeroResponse {
    @SerializedName("data")
    var data: Data? = null
}

class Data {
    @SerializedName("results")
    var results = ArrayList<Result>()
}

class Result {
    @SerializedName("title")
    var title: String = ""
    var thumbnail: Thumbnail? = null
}

class Thumbnail {
    @SerializedName("path")
    var path: String = ""
    var extension: String = ""
}
