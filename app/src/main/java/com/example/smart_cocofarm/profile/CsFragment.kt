package com.example.smart_cocofarm.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smart_cocofarm.databinding.FragmentCsBinding

class CsFragment : Fragment() {

    private var _binding: FragmentCsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCsBinding.inflate(inflater, container, false)

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}