package com.neordi6th.ddoring

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class HourPickerFragment(private val listener: (Int) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val picker = NumberPicker(activity)
        picker.minValue = 0
        picker.maxValue = 23

        builder.setView(picker)
        builder.setTitle("시간 선택")
        builder.setPositiveButton("적용") { dialog, which ->
            listener(picker.value)  // 여기서 0은 분을 의미합니다.
        }
        builder.setNegativeButton("취소", null)

        return builder.create()
    }
}