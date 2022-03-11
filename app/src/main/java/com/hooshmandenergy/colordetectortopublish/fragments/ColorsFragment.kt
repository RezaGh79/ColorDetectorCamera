package com.hooshmandenergy.colordetectortopublish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hooshmandenergy.colordetectortopublish.R
import com.hooshmandenergy.colordetectortopublish.adapter.ColorListAdapter
import com.hooshmandenergy.colordetectortopublish.database.ColorViewModel
import com.hooshmandenergy.colordetectortopublish.dialog.ColorDetailDialog
import com.hooshmandenergy.colordetectortopublish.model.UserColor
import kotlinx.android.synthetic.main.fragment_colors.*

class ColorsFragment : BottomSheetDialogFragment() {

    private val colorViewModel: ColorViewModel by lazy {
        ViewModelProvider(
            this,
            ColorViewModel.ColorViewModelFactory(requireActivity().application)
        )[ColorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_colors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorListAdapter = ColorListAdapter(requireContext()) {
            val detailDialog = ColorDetailDialog(requireContext(), it, detectColor)
            detailDialog.show()
        }

        colorViewModel.getAllColor().observe(viewLifecycleOwner) { colors ->
            val names = LinkedHashSet(colors.map { it.name }).toList()

            colorListAdapter.notifyData(names, colors)

        }

        val layoutManager = LinearLayoutManager(context)

        rv_color_list.layoutManager = layoutManager
        rv_color_list.setHasFixedSize(true)

        rv_color_list.adapter = colorListAdapter
    }

    private val detectColor: (UserColor) -> Unit = {
        colorViewModel.deleteColor(it)
    }
}