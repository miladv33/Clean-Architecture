package com.example.quotes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quotes.data.model.RandomQuote
import com.example.quotes.domain.usecase.MainUseCase
import com.example.quotes.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    fun insertRandomQuote(testDataRandomQuote: RandomQuote) {
        mainShowCase.executeRandomQuoteInsert(testDataRandomQuote)
    }
}