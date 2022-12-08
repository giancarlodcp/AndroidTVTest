package com.giancarlo.haystacktest

//TODO: Flickr Service - could not find a getList on collections.
// Use retrofit to get a Oauth + API REST
class FlickrService {

    fun getList(quantity: Int, search: String): ArrayList<FlickrPic>{
        val flickrPicList: ArrayList<FlickrPic> = ArrayList()
        (1..quantity).forEach {
            val flickrPic = FlickrPic(
                "Picture $it",
                "Author $it",
                "https://loremflickr.com/400/200/$search/all?lock=$it"
            )
            flickrPicList.add(flickrPic)
        }
        return flickrPicList
    }
}