package com.example.wichack2022.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "30 Day Vegan Challenge"
    }
    val text: LiveData<String> = _text
}