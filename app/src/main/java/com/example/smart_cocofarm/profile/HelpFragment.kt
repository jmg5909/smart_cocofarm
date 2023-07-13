package com.example.smart_cocofarm.profile

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.smart_cocofarm.R
import com.example.smart_cocofarm.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableListView = binding.expandableListView

        binding.btnKo.setOnClickListener {
            binding.koSelect.visibility = View.VISIBLE
            binding.enSelect.visibility = View.INVISIBLE
            binding.jpSelect.visibility = View.INVISIBLE

            expandableListDetail = DataPumpKo.getData()
            expandableListTitle = ArrayList(expandableListDetail.keys)
            expandableListAdapter = ExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail)
            expandableListView.setAdapter(expandableListAdapter)
        }

        binding.btnEn.setOnClickListener {
            binding.koSelect.visibility = View.INVISIBLE
            binding.enSelect.visibility = View.VISIBLE
            binding.jpSelect.visibility = View.INVISIBLE

            expandableListDetail = DataPumpEn.getData()
            expandableListTitle = ArrayList(expandableListDetail.keys)
            expandableListAdapter = ExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail)
            expandableListView.setAdapter(expandableListAdapter)
        }

        binding.btnJp.setOnClickListener {
            binding.koSelect.visibility = View.INVISIBLE
            binding.enSelect.visibility = View.INVISIBLE
            binding.jpSelect.visibility = View.VISIBLE

            expandableListDetail = DataPumpJp.getData()
            expandableListTitle = ArrayList(expandableListDetail.keys)
            expandableListAdapter = ExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail)
            expandableListView.setAdapter(expandableListAdapter)
        }

        expandableListDetail = DataPumpKo.getData()
        expandableListTitle = ArrayList(expandableListDetail.keys)
        expandableListAdapter = ExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail)
        expandableListView.setAdapter(expandableListAdapter)
    }

    private inner class ExpandableListAdapter(
        private val context: Context,
        private val expandableListTitle: List<String>,
        private val expandableListDetail: HashMap<String, List<String>>
    ) : BaseExpandableListAdapter() {

        override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
            return expandableListDetail[expandableListTitle[listPosition]]!![expandedListPosition]
        }

        override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
            return expandedListPosition.toLong()
        }

        override fun getChildView(
            listPosition: Int,
            expandedListPosition: Int,
            isLastChild: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var convertView = convertView
            val expandedListText = getChild(listPosition, expandedListPosition) as String
            if (convertView == null) {
                val layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = layoutInflater.inflate(R.layout.list_item, null)
            }
            val expandedListTextView = convertView!!.findViewById<TextView>(R.id.expandedListItem)
            expandedListTextView.text = expandedListText
            return convertView
        }

        override fun getChildrenCount(listPosition: Int): Int {
            return expandableListDetail[expandableListTitle[listPosition]]!!.size
        }

        override fun getGroup(listPosition: Int): Any {
            return expandableListTitle[listPosition]
        }

        override fun getGroupCount(): Int {
            return expandableListTitle.size
        }

        override fun getGroupId(listPosition: Int): Long {
            return listPosition.toLong()
        }

        override fun getGroupView(
            listPosition: Int,
            isExpanded: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var convertView = convertView
            val listTitle = getGroup(listPosition) as String
            if (convertView == null) {
                val layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = layoutInflater.inflate(R.layout.list_group, null)
            }
            val listTitleTextView = convertView!!.findViewById<TextView>(R.id.listTitle)
            listTitleTextView.setTypeface(null, Typeface.BOLD)
            listTitleTextView.text = listTitle
            return convertView
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
            return true
        }
    }
}