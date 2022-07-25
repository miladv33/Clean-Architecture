package com.example.quotes.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.data.enum.Error
import com.example.quotes.data.model.CustomException

/**
 * Base view model
 *
 * @constructor Create empty Base view model
 */
open class BaseViewModel: ViewModel() {
    private val _showErrorDialogLiveData = MutableLiveData<Boolean>()
    val showErrorDialogLiveData: LiveData<Boolean> = _showErrorDialogLiveData
    private val _randomQuoteErrorLiveData = MutableLiveData<Throwable>()
    val randomQuoteErrorLiveData: LiveData<Throwable> = _randomQuoteErrorLiveData

    /**
     * On failure
     *
     * @param throwable
     */
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

    /**
     * Hide dialog
     *
     */
    fun hideDialog() {
        _showErrorDialogLiveData.value = false
    }
}