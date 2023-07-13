package com.example.smart_cocofarm.profile

object DataPumpEn {
    fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()

        val shared = ArrayList<String>()
        shared.add("Shared services are not supported.")
        expandableListDetail["How do I share a device/home with friends?"] = shared

        val cancel = ArrayList<String>()
        cancel.add("Go to Cocofarm webpage or Cocofarm shopping application.")
        expandableListDetail["How do I cancel my account?"] = cancel

        val forget_password = ArrayList<String>()
        forget_password.add("Go to Cocofarm webpage or Cocofarm shopping application.")
        expandableListDetail["What should I do if I forget my password?"] = forget_password

        val watch = ArrayList<String>()
        watch.add("Smart Watch services are not supported.")
        expandableListDetail["How to user Smart Cocofarm on Smart Watch?"] = watch

        val dv_add = ArrayList<String>()
        dv_add.add("You can use the service immediately by entering the serial number at the bottom of the purchased product.")
        expandableListDetail["How do I add a new device?"] = dv_add


        return expandableListDetail
    }
}