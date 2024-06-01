package com.neordi6th.ddoring.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.R
import com.neordi6th.ddoring.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        binding.usingDdoring.setOnClickListener {
            startActivity(Intent(this, EditDdoringActivity::class.java))
        }
        binding.toDdoringRepoBtn.setOnClickListener {
            startActivity(Intent(this, DdoringRepositoryActivity::class.java))
        }

    }
}