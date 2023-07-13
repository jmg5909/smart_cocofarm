package com.example.smart_cocofarm.device

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.databinding.ActivityDvSettingBinding

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
                // DB처리
            }
            clsBuilder.setNegativeButton("취소") { dialog, which -> }
            clsBuilder.setCancelable(false)
            clsBuilder.show()
        }
    }
}