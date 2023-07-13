package com.example.smart_cocofarm.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_cocofarm.MainActivity
import com.example.smart_cocofarm.common.CommonVal
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.databinding.ActivityDeviceAddBinding
import com.example.smart_cocofarm.domain.MyDeviceVO
import com.google.gson.Gson


class DeviceAddActivity : AppCompatActivity() {
    var binding: ActivityDeviceAddBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceAddBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.tvOk.setOnClickListener {
            val vo = MyDeviceVO()
            vo.mydevice_id = binding!!.edtNumber.text.toString().toInt()
            vo.member_no = CommonVal.loginMember!!.member_no
            vo.mydevice_name = binding!!.edtName.text.toString()

            val inputNumber = binding!!.edtNumber.text.toString()
            val extracted = inputNumber.take(3)
            if (extracted.equals("107")) {
                vo.product_id = 7
            } else if (extracted.equals("108")) {
                vo.product_id = 8
            } else {
                Toast.makeText(this@DeviceAddActivity, "잘못된 일련번호입니다.", Toast.LENGTH_SHORT).show()
            }

            val conn = CommonConn(this, "/device/add_device.and")
            conn.addParam("vo", Gson().toJson( vo ))
            conn.onExecute(object : CommonConn.ConnCallback {
                override fun onResult(isResult: Boolean, data: String?) {
                    if (!isResult) {
                        return
                    } else {
                        val intent = Intent(this@DeviceAddActivity, MainActivity::class.java)
                        intent.putExtra("data", data)
                        startActivity(intent)
                        finish()
                    }
                }
            })
        }
    }
}