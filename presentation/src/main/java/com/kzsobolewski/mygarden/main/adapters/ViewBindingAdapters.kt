package com.kzsobolewski.mygarden.main.adapters

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.kzsobolewski.mygarden.search.viewmodels.SearchViewState

@BindingAdapter("isVisible")
fun setViewVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
