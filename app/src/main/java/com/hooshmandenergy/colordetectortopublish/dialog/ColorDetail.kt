package com.hooshmandenergy.colordetectortopublish.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import com.hooshmandenergy.colordetectortopublish.R
import com.hooshmandenergy.colordetectortopublish.handler.ColorDetectHandler
import com.hooshmandenergy.colordetectortopublish.model.UserColor
import kotlinx.android.synthetic.main.color_detail.*
import kotlinx.android.synthetic.main.color_detail.txt_hex
import kotlinx.android.synthetic.main.color_detail.txt_hsl
import kotlinx.android.synthetic.main.color_detail.txt_rgb
import kotlinx.android.synthetic.main.color_detail.view_color_preview
import kotlinx.android.synthetic.main.dialog_color_detail.*

class ColorDetail(
    context: Context,
    private val color: UserColor,
    private val colorDetectHandler: ColorDetectHandler
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.color_detail)
        setTitle(context.resources.getString(R.string.your_color))

        view_color_preview.setBackgroundColor(Color.parseColor(color.hex))

        colorDetectHandler.convertRgbToHsl(color)

        txt_rgb.text = "RGB(${color.r}, ${color.g}, ${color.b})"
        txt_hex.text = "Hex : ${color.hex}"
        txt_hsl.text = "HSL(${color.h}, ${color.s}, ${color.l})"


    }
}