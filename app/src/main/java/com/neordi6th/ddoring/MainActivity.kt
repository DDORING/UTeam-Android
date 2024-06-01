package com.neordi6th.ddoring

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.databinding.ActivityHomeBinding

class MainActivity : AppCompatActivity() {

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


        binding.nextBtn2.setOnClickListener{

            v1.visibility = View.GONE
            v2.visibility = View.GONE
            v3.visibility = View.GONE

        }





    }


}