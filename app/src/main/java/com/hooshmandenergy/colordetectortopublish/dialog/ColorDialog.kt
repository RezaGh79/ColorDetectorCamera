package com.hooshmandenergy.colordetectortopublish.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hooshmandenergy.colordetectortopublish.R
import com.hooshmandenergy.colordetectortopublish.adapter.ColorAdapter
import com.hooshmandenergy.colordetectortopublish.database.ColorViewModel
import kotlinx.android.synthetic.main.color_list_item.*
import kotlinx.android.synthetic.main.color_list_item.rv_color
import kotlinx.android.synthetic.main.dialog_color.*
import kotlinx.android.synthetic.main.dialog_color_detail.*
import kotlinx.android.synthetic.main.dialog_color_detail.btn_cancel

class ColorDialog(
    context: Context,
    private val colorViewModel: ColorViewModel,
    private val colorAdapter: ColorAdapter,
    private val onClearColor: () -> Unit
) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_color)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_color.layoutManager = layoutManager
        rv_color.setHasFixedSize(true)
        rv_color.adapter = colorAdapter

        btn_add_color.setOnClickListener {
            val name = edt_name_of_list.text.toString()

            if (name.isNotEmpty()) {
                colorAdapter.colors.forEach {
                    it.name = name
                    colorViewModel.insertColor(it)
                }
                //colorViewModel.insertAllColor(colorAdapter.colors)
                onClearColor()
                dismiss()
            }
        }

        btn_cancel.setOnClickListener {
            dismiss()
        }
    }
}