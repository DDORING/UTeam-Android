package com.neordi6th.ddoring

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()  {

    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//
//        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val v1 = binding.profileTalk
        val v2 = binding.profileTalk
        val v3 = binding.fixCircleBtn
        val v4 = binding.pauseBtn


        binding.nextBtn2.setOnClickListener{

            v1.visibility = View.GONE
            v2.visibility = View.GONE
            v3.visibility = View.GONE
            v4.visibility = View.VISIBLE

        }





    }

}