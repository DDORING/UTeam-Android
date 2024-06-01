package com.neordi6th.ddoring

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.neordi6th.ddoring.data.dto.NotificationRequest
import com.neordi6th.ddoring.data.network.RetrofitClient
import com.neordi6th.ddoring.data.service.NotificationService
import com.neordi6th.ddoring.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName
    private val notificationService =
        RetrofitClient.getLoggedInInstance().create(NotificationService::class.java)
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

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val v1 = binding.profileTalk
//        val v2 = binding.profileTalk
//        val v3 = binding.fixCircleBtn
//        val v4 = binding.pauseBtn
//
//
//        binding.nextBtn2.setOnClickListener{
//
//            v1.visibility = View.GONE
//            v2.visibility = View.GONE
//            v3.visibility = View.GONE
//            v4.visibility = View.VISIBLE
//
//        }


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            val token = task.result
            Log.d(TAG, "onCreate: $token")
            notificationService.saveToken(NotificationRequest(token, "test", "test")).enqueue(object :
                Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Token successfully saved to server.")
                    } else {
                        Log.e(TAG, "Failed to save token to server. Response code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e(TAG, "Error saving token to server: ${t.localizedMessage}")
                }
            })
        })
    }
}


