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

    private var imageUrls = ArrayList<String>()

    fun getNewImages() {
        viewModelScope.launch(Dispatchers.IO) {
            imageUrls.addAll(CrawlGettyImages.getInstance().nextImagesUrls)
            imageUrlsLiveData.postValue(imageUrls)
        }
    }
}