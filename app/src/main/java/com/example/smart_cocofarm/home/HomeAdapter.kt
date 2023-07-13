package com.example.smart_cocofarm.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.databinding.ItemDeviceListBinding
import com.example.smart_cocofarm.device.DeviceActivity
import com.example.smart_cocofarm.domain.MyDeviceVO

class HomeAdapter(private val list: List<MyDeviceVO>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ItemDeviceListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vo: MyDeviceVO = list[position]
        holder.bind(vo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemDeviceListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyDeviceVO) {
            binding.tvDvName.text = item.mydevice_name
            if (item.product_id == 7) {
                binding.ivProductImg.setImageResource(R.drawable.devicemini)
                binding.tvProduct.text = "띄우운 미니"
            } else {
                binding.ivProductImg.setImageResource(R.drawable.devicebig)
                binding.tvProduct.text = "띄우운 오브제"
            }
            binding.go.setOnClickListener {
                val intent = Intent(context, DeviceActivity::class.java)
                intent.putExtra("dvname", item.mydevice_name)
                intent.putExtra("dvid", item.mydevice_id)
                context?.startActivity(intent)
            }

        }
    }
}
