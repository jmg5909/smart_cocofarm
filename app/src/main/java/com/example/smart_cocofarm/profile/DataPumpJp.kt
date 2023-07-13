package com.example.smart_cocofarm.profile

object DataPumpJp {
    fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()

        val shared = ArrayList<String>()
        shared.add("共有サービスはサポートしていません。")
        expandableListDetail["デバイス/ホームを友達と共有するにはどうすればいいですか？"] = shared

        val cancel = ArrayList<String>()
        cancel.add("会員退会は、ココファームのウェブページまたはショッピングアプリでできます。")
        expandableListDetail["アカウントをキャンセルするにはどうすればいいですか？"] = cancel

        val forget_password = ArrayList<String>()
        forget_password.add("パスワードの検索は、ココファームのウェブページまたはショッピングアプリでできます。")
        expandableListDetail["パスワードを忘れた場合はどうすればいいですか？"] = forget_password

        val watch = ArrayList<String>()
        watch.add("Smart Watchサービスはサポートしていません。")
        expandableListDetail["Smart WatchでSmart Cocofarmを使用するにはどうすればよいですか？"] = watch

        val dv_add = ArrayList<String>()
        dv_add.add("ご購入いただいた製品の下段にシリアル番号を入力していただくと、すぐにサービスをご利用いただけます。")
        expandableListDetail["どのように新しい機器を追加しますか？"] = dv_add


        return expandableListDetail
    }
}