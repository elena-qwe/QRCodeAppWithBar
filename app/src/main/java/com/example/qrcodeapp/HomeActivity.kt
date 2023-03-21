package com.example.qrcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.qrcodeapp.databinding.ActivityHomeBinding
import com.example.qrcodeapp.fragments.history.ScanHistoryFragment
import com.example.qrcodeapp.fragments.mark.MarkFragment
import com.example.qrcodeapp.fragments.scan.ScanFragment
import com.example.qrcodeapp.fragments.settings.SettingsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ScanFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.scan -> replaceFragment(ScanFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
                R.id.mark -> replaceFragment(MarkFragment())
                R.id.history -> replaceFragment(ScanHistoryFragment())
                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}