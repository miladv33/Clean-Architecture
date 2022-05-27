package com.example.takehomeassesmenttestnumber1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.takehomeassesmenttestnumber1.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainShowCase: MainUseCase
) : ViewModel() {

}