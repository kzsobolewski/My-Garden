package com.kzsobolewski.mygarden.main.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.ActivityMainBinding
import com.kzsobolewski.mygarden.plants.fragments.INavigationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.isToolbarImageVisible = true

        setSupportActionBar(main_toolbar as Toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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

    fun showUpNavigation() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    fun hideUpNavigation() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    fun showToolbarImage(isVisible: Boolean) {
        binding.isToolbarImageVisible = isVisible
    }
}