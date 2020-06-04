package com.kzsobolewski.mygarden.main.adapters

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setViewVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

