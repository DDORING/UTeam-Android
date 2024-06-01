package com.neordi6th.ddoring.ui.mypage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.R
import com.neordi6th.ddoring.databinding.ActivityDdoringRepositoryBinding

class DdoringRepositoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDdoringRepositoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ddoring_repository)

        binding.ddoringRepoRv.apply {

        }

    }
}