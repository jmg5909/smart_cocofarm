package com.example.smart_cocofarm.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.common.CommonVal
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.databinding.FragmentHomeBinding
import com.example.smart_cocofarm.domain.MyDeviceVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var list: ArrayList<MyDeviceVO> = ArrayList<MyDeviceVO>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.tvUser.text = CommonVal.loginMember?.nickname + "님 반갑습니다"

        binding.btnAdd.setOnClickListener {
            val menu = PopupMenu(requireContext(), binding.btnAdd)
            menu.menuInflater.inflate(R.menu.board_seemore, menu.menu)
            menu.setOnMenuItemClickListener { item: MenuItem ->
                val itemId = item.itemId
                Log.d("더보기", "onCreate: $itemId")
                if (itemId == R.id.add_device) {
                    val intent = Intent(context, DeviceAddActivity::class.java)
                    startActivity(intent)
                } else if (itemId == R.id.qr_scan) {
                    Toast.makeText(context, "QR 스캔", Toast.LENGTH_SHORT).show()
                }
                false
            }
            menu.show()
        }

        val conn = CommonConn(requireContext(), "/device/mydevice.and")
        conn.addParam("member_no", CommonVal.loginMember!!.member_no)
        conn.onExecute(object : CommonConn.ConnCallback {
            override fun onResult(isResult: Boolean, data: String?) {
                Log.d("데이터", "onResult: $data")

                if (!isResult) {
                    return
                } else {
                    val deviceVO = Gson().fromJson<List<MyDeviceVO>>(data,   object : TypeToken<List<MyDeviceVO>>() {}.type)
                    if (deviceVO != null) {
                       // list = arrayListOf(deviceVO)
                        val homeAdapter = HomeAdapter(deviceVO)
                        val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recv.adapter = homeAdapter
                        binding.recv.layoutManager = manager
                    } else {
                        binding.tvNoResult.visibility = View.VISIBLE
                    }
                }
            }
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}