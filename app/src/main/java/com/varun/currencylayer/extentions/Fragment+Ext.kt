package com.varun.currencylayer.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.varun.currencylayer.ui.ViewModelFactory

fun <T : ViewModel> Fragment.getViewModel(
    viewModelFactory: ViewModelFactory,
    modelClass: Class<T>
): T {
    return ViewModelProvider(this, viewModelFactory).get(modelClass)
}