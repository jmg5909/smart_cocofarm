package com.example.smart_cocofarm.conn

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonConn(private val context: Context, private val mapping: String) {
    private val params: HashMap<String, Any> = HashMap()
    private var callback: ConnCallback? = null
    private var dialog: ProgressDialog? = null


    init {
        params.clear()
    }

    constructor(context: Context, mapping: String, params: HashMap<String, Any>) : this(context, mapping) {
        this.params.putAll(params)
    }

    fun addParam(key: String, value: Any?) {
        if (key != null && value != null) {
            params[key] = value
        }
    }

    private fun onPreExecute() {
        if (context != null && dialog == null) {
            dialog = ProgressDialog(context)
            dialog?.apply {
                setProgress(ProgressDialog.STYLE_SPINNER)
                setCancelable(false)
                setTitle("코코팜")
                setMessage("잠시만 기다려주세요..")
                show()
            }
        }
    }

    fun onExecute(callback: ConnCallback) {
        onPreExecute()
        this.callback = callback
        val apiInterface = Service.getApiClient().create(ApiInterface::class.java)
        apiInterface.getPost(mapping, params).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Post 요청", "onResponse: ")
                if (response.isSuccessful) {
                    onPostExecute(true, response.body())
                    return
                }
                Toast.makeText(context, "오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Post", "onFailure: " + t.javaClass.name)
                if (t.javaClass.name == "java.net.SocketTimeoutException" && context != null) {
                    onPostExecute(false, t.message)
                    dialog?.dismiss()
                }
            }
        })
    }

    private fun onPostExecute(isResult: Boolean, data: String?) {
        dialog?.dismiss()
        callback?.onResult(isResult, data)
    }

    interface ConnCallback {
        fun onResult(isResult: Boolean, data: String?)
    }
}