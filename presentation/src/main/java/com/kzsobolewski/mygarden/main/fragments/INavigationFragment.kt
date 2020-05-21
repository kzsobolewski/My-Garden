package com.kzsobolewski.mygarden.main.fragments

import com.kzsobolewski.mygarden.main.activities.MainActivity

interface INavigationFragment {

    val mainActivity: MainActivity

    fun onBackPressed(): Boolean{
        mainActivity.apply {
        setUpNavigationVisibility(false)
        setLogoVisibility(true)
        title = ""
    }
    return false
}
}