package com.example.smart_cocofarm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smart_cocofarm.databinding.ActivityMainBinding
import com.example.smart_cocofarm.home.HomeFragment
import com.example.smart_cocofarm.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var mContext: Context
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this

        binding.bottomNav.setOnItemSelectedListener { menu ->
            val fragment: Fragment = when (menu.itemId) {
                R.id.home -> HomeFragment()
                R.id.stats -> HomeFragment()
                R.id.profile -> ProfileFragment()
                else -> return@setOnItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
            true
        }
        binding.bottomNav.selectedItemId = R.id.home
    }
}