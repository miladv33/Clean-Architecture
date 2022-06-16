package com.example.takehomeassesmenttestnumber1.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takehomeassesmenttestnumber1.data.enum.Error
import com.example.takehomeassesmenttestnumber1.data.model.CustomException
import com.example.takehomeassesmenttestnumber1.data.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.domain.usecase.MainUseCase
import com.example.takehomeassesmenttestnumber1.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainShowCase: MainUseCase
) : BaseViewModel() {

    private val _randomQuoteLiveData = MutableLiveData<RandomQuote>()
    val randomQuoteLiveData: LiveData<RandomQuote> = _randomQuoteLiveData

    fun getQuotesFlow() {
        viewModelScope.launch {
            mainShowCase.executeQuotesFlow().flowOn(Dispatchers.IO).collect {
                it.onSuccess { baseRandomQuote ->
                    _randomQuoteLiveData.value = baseRandomQuote
                }
                it.onFailure { throwable ->
                    onFailure(throwable)
                }
            }
        }
    }
}