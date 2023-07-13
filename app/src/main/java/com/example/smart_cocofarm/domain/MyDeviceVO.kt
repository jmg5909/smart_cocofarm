package com.example.smart_cocofarm.domain

import com.example.smart_cocofarm.util.DateJsonAdapter
import com.google.gson.annotations.JsonAdapter
import lombok.Data
import java.util.*

@Data
class MyDeviceVO {
    var mydevice_id = 0
    var member_no = 0
    var product_id = 0
    var mydevice_name: String? = null
    @JsonAdapter(DateJsonAdapter::class)
    var regdate: Date? = null
}