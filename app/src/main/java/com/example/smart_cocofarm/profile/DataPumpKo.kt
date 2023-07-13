package com.example.smart_cocofarm.profile

object DataPumpKo {
    fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()

        val shared = ArrayList<String>()
        shared.add("공유 서비스는 지원하고 있지 않습니다.")
        expandableListDetail["장치/홈을 친구와 공유하려면 어떻게 해야 하나요?"] = shared

        val cancel = ArrayList<String>()
        cancel.add("회원탈퇴는 코코팜 웹페이지 또는 쇼핑 앱에서 할 수 있습니다.")
        expandableListDetail["회원탈퇴는 어떻게 할 수 있나요?"] = cancel

        val forget_password = ArrayList<String>()
        forget_password.add("비밀번호 찾기는 코코팜 웹페이지 또는 쇼핑 앱에서 할 수 있습니다.")
        expandableListDetail["비밀번호를 분실 했을 경우 어떻게 해야하나요?"] = forget_password

        val watch = ArrayList<String>()
        watch.add("스마트워치 서비스는 지원하고 있지 않습니다.")
        expandableListDetail["스마트워치에서 어플을 사용하는 방법이 있나요?"] = watch

        val dv_add = ArrayList<String>()
        dv_add.add("구매하신 제품 하단의 일련번호를 입력하시면 즉시 서비스를 이용하실 수 있습니다.")
        expandableListDetail["어떻게 새 기기를 추가하나요?"] = dv_add


        return expandableListDetail
    }
}