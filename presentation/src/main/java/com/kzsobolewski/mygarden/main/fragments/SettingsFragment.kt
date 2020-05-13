package com.kzsobolewski.mygarden.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.main.activities.MainActivity

class SettingsFragment : Fragment(), INavigationFragment {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).apply {
            setUpNavigationVisibility(true)
            setLogoVisibility(false)
        }
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onBackPressed(): Boolean {
        (activity as MainActivity).apply {
            setUpNavigationVisibility(false)
            setLogoVisibility(true)
        }
        return false
    }

}
