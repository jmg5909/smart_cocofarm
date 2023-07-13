package com.example.smart_cocofarm.domain

import com.example.smart_cocofarm.util.DateJsonAdapter
import com.google.gson.annotations.JsonAdapter
import lombok.Data
import java.io.Serializable
import java.util.*

@Data
class MemberVO (
    var member_no: Int,
    var nickname: String,
    var member_type_cd: Int,
    var email: String,
    var password: String,
    var phonenumber: String,
    var address: String,
    @JsonAdapter(DateJsonAdapter::class)
    var joindate: Date,
    var isactivated: String,
    var sns: String,
    var m_question: String,
    var m_answer: String
) : Serializable