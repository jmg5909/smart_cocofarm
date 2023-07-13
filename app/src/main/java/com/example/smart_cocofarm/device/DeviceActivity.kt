package com.example.smart_cocofarm.device

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smart_cocofarm.LoginActivity
import com.example.smart_cocofarm.databinding.ActivityDeviceBinding
import com.example.smart_cocofarm.databinding.ActivityLoginBinding

class DeviceActivity : AppCompatActivity() {
    var binding: ActivityDeviceBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var dvname = intent.getSerializableExtra("dvname")
        var dvid = intent.getSerializableExtra("dvid")

        binding!!.tvDeviceName.text = dvname.toString()

        binding!!.setting.setOnClickListener {
            val intent = Intent(this@DeviceActivity, DvSettingActivity::class.java)
            intent.putExtra("dvname", dvname)
            intent.putExtra("dvid", dvid)
            startActivity(intent)
        }
    }
}