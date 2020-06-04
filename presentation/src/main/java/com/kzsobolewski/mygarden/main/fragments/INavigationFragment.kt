package com.kzsobolewski.mygarden.main.fragments

import com.kzsobolewski.mygarden.main.activities.MainActivity

interface INavigationFragment {

    val mainActivityForNavigation: MainActivity

    fun onBackPressed(): Boolean{
        mainActivityForNavigation.apply {
        setUpNavigationVisibility(false)
        setLogoVisibility(true)
        title = ""
    }
    return false
}
}