package com.example.smart_cocofarm.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smart_cocofarm.LoginActivity
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.common.CommonVal
import com.example.smart_cocofarm.common.CommonVal.loginMember
import com.example.smart_cocofarm.conn.CommonConn
import com.example.smart_cocofarm.databinding.FragmentProfileBinding
import com.example.smart_cocofarm.domain.MemberVO
import com.example.smart_cocofarm.util.ConfirmDialog
import com.example.smart_cocofarm.util.ConfirmDialogInterface

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.username.text = CommonVal.loginMember?.nickname.toString()

        val conn = CommonConn(requireContext(), "/device/cnt_device.and")
        conn.addParam("member_no", CommonVal.loginMember?.member_no)
        conn.onExecute(object : CommonConn.ConnCallback {
            override fun onResult(isResult: Boolean, data: String?) {
                if (!isResult) {
                    binding.count.text = "알 수 없음"
                    return
                } else {
                    Log.d("갯수", "onResult: $data")
                    binding.count.text = data + "개 기기"
                }
            }
        })

        // 법률 정보
        binding.selectLaw.setOnClickListener {
            val lawFragment = LawFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, lawFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // 앱 정보
        binding.selectAppinfo.setOnClickListener {
            val appInfoFragment = AppInfoFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, appInfoFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // 고객센터
        binding.selectCs.setOnClickListener {
            val csFragment = CsFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, csFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // 도움말
        binding.selectHelp.setOnClickListener {
            val helpFragment = HelpFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, helpFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // 로그아웃
        binding.selectLogout.setOnClickListener {
            val confirmDialog = ConfirmDialog(object : ConfirmDialogInterface {
                override fun onYesButtonClick(id: Int) {
                    // 로그아웃 처리하는 구간
                    loginMember = null
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
            }, "로그아웃하시겠습니까?", 123)

            confirmDialog.show(requireActivity().supportFragmentManager, "ConfirmDialog")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}