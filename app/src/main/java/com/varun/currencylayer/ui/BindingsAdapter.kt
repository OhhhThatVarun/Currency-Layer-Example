package com.varun.currencylayer.ui

import android.widget.ImageView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.varun.currencylayer.ui.adapters.SpinnerAdapter

@BindingAdapter("flag")
fun bindImage(imageView: ImageView, resource: Int) {
    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, resource))
}

@BindingAdapter("spinnerAdapter")
fun bindSpinnerAdapter(spinner: Spinner, spinnerAdapter: SpinnerAdapter) {
    spinner.adapter = spinnerAdapter
}