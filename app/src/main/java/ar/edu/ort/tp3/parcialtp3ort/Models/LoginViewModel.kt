package ar.edu.ort.tp3.parcialtp3ort.Models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel:ViewModel() {

    val usuario = MutableLiveData<String>()
}