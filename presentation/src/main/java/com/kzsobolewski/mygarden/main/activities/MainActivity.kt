package com.kzsobolewski.mygarden.main.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.ActivityMainBinding
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // TODO
    //dark theme
    //toolbar text colors
        
    //design change on trefle list
    //image visible in plant info fragment
    //changing language in settings


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.isToolbarImageVisible = true
        setSupportActionBar(main_toolbar as Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_option -> goToSettingsFragment()
            R.id.about_option -> goToAboutFragment()
            android.R.id.home -> goBack()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToSettingsFragment(): Boolean {
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.action_tabsFragment_to_settingsFragment)
        return true
    }

    private fun goToAboutFragment(): Boolean {
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.action_tabsFragment_to_aboutFragment)
        return true
    }

    private fun goBack(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val handled =
            (supportFragmentManager
                .primaryNavigationFragment
                ?.childFragmentManager
                ?.primaryNavigationFragment as? INavigationFragment)?.onBackPressed() ?: false
        if (!handled)
            super.onBackPressed()
    }

    fun setUpNavigationVisibility(value: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(value)
            setDisplayShowHomeEnabled(value)
        }
    }

    fun setLogoVisibility(value: Boolean) {
        binding.isToolbarImageVisible = value
    }

    fun setToolbarForSideScreen(title: String?) {
        setUpNavigationVisibility(true)
        setLogoVisibility(false)
        this.title = title ?: ""
    }

    fun setNightMode(value: Boolean) {
        binding.isNightMode = value
    }
}
