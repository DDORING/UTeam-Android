package com.neordi6th.ddoring.ui.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.R
import com.neordi6th.ddoring.databinding.ActivityEditDdoringBinding

class EditDdoringActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDdoringBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_ddoring)
    }
}