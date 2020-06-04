package com.kzsobolewski.mygarden.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.main.activities.MainActivity

class AboutFragment : Fragment(), INavigationFragment {

    override val mainActivityForNavigation: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setToolbarForSideScreen(getString(R.string.about))
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}
