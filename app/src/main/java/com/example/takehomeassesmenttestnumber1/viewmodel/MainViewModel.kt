package com.example.takehomeassesmenttestnumber1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takehomeassesmenttestnumber1.model.RandomQuote
import com.example.takehomeassesmenttestnumber1.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainShowCase: MainUseCase
) : ViewModel() {

    private val _showErrorDialogLiveData = MutableLiveData<Boolean>()
    val showErrorDialogLiveData: LiveData<Boolean> = _showErrorDialogLiveData
    private val _randomQuoteErrorLiveData = MutableLiveData<Throwable>()
    val randomQuoteErrorLiveData: LiveData<Throwable> = _randomQuoteErrorLiveData
    private val _randomQuoteLiveData = MutableLiveData<RandomQuote>()
    val randomQuoteLiveData: LiveData<RandomQuote> = _randomQuoteLiveData


    fun getQuotesFlow() {
        viewModelScope.launch {
            mainShowCase.executeQuotesFlow().collect {
                it.onSuccess {
                    _randomQuoteLiveData.value = it
                }
                it.onFailure { throwable ->
                    _showErrorDialogLiveData.value = true
                    _randomQuoteErrorLiveData.value = throwable
                }
            }
        }
    }

    fun hideDialog() {
        _showErrorDialogLiveData.value = false
    }

}