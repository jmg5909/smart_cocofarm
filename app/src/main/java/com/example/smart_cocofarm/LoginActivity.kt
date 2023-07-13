package com.example.smart_cocofarm

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_cocofarm.common.CommonVal
import com.example.smart_cocofarm.common.CommonVal.loginMember
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.databinding.ActivityLoginBinding
import com.example.smart_cocofarm.domain.MemberVO
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        preferences = getPreferences(MODE_PRIVATE)

        if (CommonVal.isCheckLogout) {
            saveLoginInfo()
        }


        binding!!.btnLogin.setOnClickListener {
            val email = binding!!.edtId.text.toString()
            val password = binding!!.edtPw.text.toString()
            login(email, password, this)
            saveLoginInfo()
        }
    }


    // Login Function
    fun login(email: String?, pw: String?, activity: Activity) {
        val conn = CommonConn(activity, "/member/login.and")
        conn.addParam("email", email)
        conn.addParam("password", pw)

        conn.onExecute(object : CommonConn.ConnCallback {
            override fun onResult(isResult: Boolean, data: String?) {
                if (!isResult) {
                    Toast.makeText(activity, "로그인 서버가 응답하지 않습니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                if (data == null || data.equals("null")) {
                    Toast.makeText(activity, "이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    return
                }

                loginMember = Gson().fromJson(data, MemberVO::class.java)
                val intent = Intent(activity, MainActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
        })
    }


    // 로그인 정보 저장
    private fun saveLoginInfo() {
        val editor: SharedPreferences.Editor = preferences.edit()
        if (binding != null && binding!!.chkLogin.isChecked) {
            editor.putString("email", binding!!.edtId.text.toString())
            editor.putString("password", binding!!.edtPw.text.toString())
            editor.putBoolean("checked", true)
        } else {
            editor.putString("email", "")
            editor.putString("password", "")
            editor.putBoolean("checked", false)
        }
        editor.apply()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}