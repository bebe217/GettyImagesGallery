package com.example.gettyimagesgallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListViewModel : ViewModel() {
    val imageUrlsLiveData: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    private val crawler = CrawlGettyImages.getInstance()
    private var imageUrls = ArrayList<String>()

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            imageUrls.addAll(crawler.imageUrls)
            imageUrlsLiveData.postValue(imageUrls)
        }
    }

    fun getNextImages() {
        viewModelScope.launch(Dispatchers.IO) {
            imageUrls.addAll(crawler.nextImagesUrls)
            imageUrlsLiveData.postValue(imageUrls)
        }
    }
}