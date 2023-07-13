package com.example.smart_cocofarm.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smart_cocofarm.databinding.FragmentLawBinding

class LawFragment : Fragment() {

    private var _binding: FragmentLawBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLawBinding.inflate(inflater, container, false)

        binding.firsttab.setOnClickListener {
            val urlintent = Intent(Intent.ACTION_VIEW, Uri.parse("http://localhost:9090/termsofuse"))
            startActivity(urlintent)
        }

        binding.secondtab.setOnClickListener {
            val urlintent = Intent(Intent.ACTION_VIEW, Uri.parse("http://localhost:9090/privacypolicy"))
            startActivity(urlintent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}