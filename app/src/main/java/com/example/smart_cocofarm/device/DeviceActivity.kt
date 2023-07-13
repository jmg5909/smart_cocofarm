package com.example.smart_cocofarm.device

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smart_cocofarm.LoginActivity
import com.example.smart_cocofarm.conn.ApiInterface
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.conn.Service
import com.example.smart_cocofarm.databinding.ActivityDeviceBinding
import com.example.smart_cocofarm.databinding.ActivityLoginBinding
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeviceActivity : AppCompatActivity() {
    var binding: ActivityDeviceBinding? = null
    val tempLiveData = MutableLiveData<String>()
    val humiLiveData = MutableLiveData<String>()
    val illuLiveData = MutableLiveData<String>()

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


        val handler = Handler()
        // 일정 시간 간격으로 요청을 보내고 응답을 처리합니다.
        val updateRunnable = object : Runnable {

            override fun run() {
                val apiInterface = Service.getApiClient("http://192.168.0.181/").create(ApiInterface::class.java)
                apiInterface.getGet("data").enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        val result = response.body().toString()
                        val jsonobj = JSONObject(result)

                        val temp = (jsonobj.get("temp") as CharSequence?).toString() + "℃"
                        val humi = (jsonobj.get("h") as CharSequence?).toString() + "%"
                        val illu = (jsonobj.get("b") as CharSequence?).toString()

                        tempLiveData.postValue(temp)
                        humiLiveData.postValue(humi)
                        illuLiveData.postValue(illu)

                        runOnUiThread {
                            tempLiveData.observe(this@DeviceActivity) { temp ->
                                binding!!.temp.text = temp
                            }
                            humiLiveData.observe(this@DeviceActivity) { humi ->
                                binding!!.humi.text = humi
                            }
                            illuLiveData.observe(this@DeviceActivity) { illu ->
                                binding!!.illu.text = illu
                            }
                        }
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("get", "onFailure: " + t.javaClass.name)
                    }
                })
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateRunnable)
    }
}