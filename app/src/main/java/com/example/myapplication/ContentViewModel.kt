package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.io.InputStreamReader

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    private val _contentLiveData = MutableLiveData<List<Content>>()
    val contentLiveData: LiveData<List<Content>> get() = _contentLiveData

    // Load the content from the JSON
    fun loadContent() {
        val contentResponse = parseJson()
        _contentLiveData.value = contentResponse.content
    }

    // Parse the JSON content
    private fun parseJson(): ContentResponse {
        val inputStream = getApplication<Application>().resources.openRawResource(R.raw.carousal) // assuming JSON file in res/raw
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson(reader, ContentResponse::class.java)
    }
}
