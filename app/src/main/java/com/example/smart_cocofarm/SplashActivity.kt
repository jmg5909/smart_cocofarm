package com.example.smart_cocofarm

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_cocofarm.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    var binding: ActivitySplashBinding? = null
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getPreferences(MODE_PRIVATE)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())

        Handler(Looper.getMainLooper()).postDelayed({
            val isChecked: Boolean = preferences.getBoolean("checked", false)
            if (isChecked) {
                val email: String? = preferences.getString("email", "")
                val pw: String? = preferences.getString("password", "")
                LoginActivity().login(email, pw, this)
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }, (1 * 1500).toLong())
    }
}