package com.example.takehomeassesmenttestnumber1.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.takehomeassesmenttestnumber1.data.enum.Error
import com.example.takehomeassesmenttestnumber1.data.model.CustomException

open class BaseViewModel: ViewModel() {
    private val _showErrorDialogLiveData = MutableLiveData<Boolean>()
    val showErrorDialogLiveData: LiveData<Boolean> = _showErrorDialogLiveData
    private val _randomQuoteErrorLiveData = MutableLiveData<Throwable>()
    val randomQuoteErrorLiveData: LiveData<Throwable> = _randomQuoteErrorLiveData

    protected fun onFailure(throwable: Throwable) {
        if (throwable is CustomException) {
            when (throwable.error) {
                Error.NullObject -> {
                }
            }
        } else {
            _showErrorDialogLiveData.value = true
            _randomQuoteErrorLiveData.value = throwable
        }
    }

    fun hideDialog() {
        _showErrorDialogLiveData.value = false
    }
}