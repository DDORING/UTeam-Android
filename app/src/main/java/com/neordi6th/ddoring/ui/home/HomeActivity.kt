package com.neordi6th.ddoring.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neordi6th.ddoring.HourPickerFragment
import com.neordi6th.ddoring.MinutePickerFragment
import com.neordi6th.ddoring.R
import com.neordi6th.ddoring.databinding.ActivityHomeBinding
import com.neordi6th.ddoring.ui.mypage.MypageActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var countDownTimer: CountDownTimer? = null
    private var totalTimeInMinutes: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.toMypage.setOnClickListener {
            startActivity(Intent(this, MypageActivity::class.java))
        }

        binding.apply {
            hourBtn.setOnClickListener {
                val hourPickerFragment = HourPickerFragment { selectedHour ->
                    binding.hour.text = selectedHour.toString()
                }
                hourPickerFragment.show(supportFragmentManager, "hourPicker")
            }

            minuteBtn.setOnClickListener {
                val minutePickerFragment = MinutePickerFragment { selectedMinute ->
                    binding.minute.text = selectedMinute.toString()
                }
                minutePickerFragment.show(supportFragmentManager, "minutePicker")
            }
        }

        binding.nextBtn2.setOnClickListener {
            val hours = binding.hour.text.toString().toLongOrNull() ?: 0
            val minutes = binding.minute.text.toString().toLongOrNull() ?: 0
            totalTimeInMinutes = hours * 60 + minutes

            startTimer(totalTimeInMinutes)
            Toast.makeText(this@HomeActivity, "도파민 분출", Toast.LENGTH_LONG).show()
        }
    }

    private fun startTimer(timeInMinutes: Long) {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(timeInMinutes * 60000, 60000) {
            override fun onTick(millisUntilFinished: Long) {
                totalTimeInMinutes = millisUntilFinished / 60000
                updateTimerText()
            }

            override fun onFinish() {
                // push fcm alarm
            }
        }.start()
    }

    private fun updateTimerText() {
        val hours = totalTimeInMinutes / 60
        val minutes = totalTimeInMinutes % 60

        binding.hour.text = hours.toString()
        binding.minute.text = minutes.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

}
