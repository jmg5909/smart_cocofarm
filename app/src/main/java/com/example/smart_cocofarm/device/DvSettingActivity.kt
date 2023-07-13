package com.example.smart_cocofarm.device

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_cocofarm.MainActivity
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.common.CommonVal
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.databinding.ActivityDvSettingBinding
import com.example.smart_cocofarm.domain.MyDeviceVO


class DvSettingActivity : AppCompatActivity() {
    var binding: ActivityDvSettingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDvSettingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var dvname = intent.getSerializableExtra("dvname")
        var dvid = intent.getSerializableExtra("dvid")

        binding!!.dvname.text = dvname.toString()

        binding!!.modifytab.setOnClickListener {
            val txtEdit = EditText(this)

            val clsBuilder = AlertDialog.Builder(this, R.style.MyDialogTheme)
            clsBuilder.setTitle("변경하실 닉네임을 입력해주세요")
            clsBuilder.setView(txtEdit)
            clsBuilder.setPositiveButton("확인") { dialog, which ->
                val strText = txtEdit.text.toString()

                val vo = MyDeviceVO()
                vo.member_no = CommonVal.loginMember!!.member_no
                vo.mydevice_name = strText
                vo.mydevice_id = dvid as Int

                val conn = CommonConn(this@DvSettingActivity, "/device/dvname_modify.and")
                conn.addParam("member_no", CommonVal.loginMember!!.member_no)
                conn.addParam("mydevice_name", strText)
                conn.addParam("mydevice_id", dvid)

                conn.onExecute(object : CommonConn.ConnCallback {
                    override fun onResult(isResult: Boolean, data: String?) {
                        if (!isResult) {
                            return
                        } else {
                            val intent = Intent(this@DvSettingActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            Toast.makeText(this@DvSettingActivity, "기기 이름이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            clsBuilder.setNegativeButton("취소") { dialog, which -> }
            clsBuilder.setCancelable(false)
            clsBuilder.show()
        }
    }
}