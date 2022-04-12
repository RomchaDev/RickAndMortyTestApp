package com.romeo.rickandmortytestapp.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.romeo.rickandmortytestapp.R
import com.romeo.rickandmortytestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.fragment_container)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.itemIconTintList = null

        navController.addOnDestinationChangedListener { _, dest, _ ->
            dest.id.let { id ->
                if (id == R.id.charactersFragment ||
                    id == R.id.favoritesFragment
                )
                    setBottomNavigationVisible(true)
                else
                    setBottomNavigationVisible(false)
            }
        }
    }

    private fun setBottomNavigationVisible(isVisible: Boolean) {
        binding.bottomNavigation.visibility =
            if (isVisible) View.VISIBLE
            else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}