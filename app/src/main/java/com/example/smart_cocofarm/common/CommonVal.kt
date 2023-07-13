package com.example.smart_cocofarm.common

import com.example.smart_cocofarm.domain.MemberVO
import com.example.smart_cocofarm.domain.MyDeviceVO
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object CommonVal {
    var loginMember: MemberVO? = null
    var deviceInfo: MyDeviceVO? = null
    var isCheckLogout: Boolean = false

    val Md: SimpleDateFormat = SimpleDateFormat("M월 d일")
    val yyyyMMdd: SimpleDateFormat = SimpleDateFormat("yyyyMMdd")
    val yyyyMMddHHmmss: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val HHmmss: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    fun isToday(date: Date): Boolean {
        return yyyyMMdd.format(date) == yyyyMMdd.format(Date())
    }

    fun comma(money: Int): String {
        return "￦ " + DecimalFormat("###,###").format(money) + "원"
    }
}