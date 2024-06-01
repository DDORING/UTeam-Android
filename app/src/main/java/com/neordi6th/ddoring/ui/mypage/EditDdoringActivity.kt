package com.neordi6th.ddoring.ui.mypage

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neordi6th.ddoring.R
import com.neordi6th.ddoring.databinding.ActivityEditDdoringBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class EditDdoringActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDdoringBinding
    private val viewModel by viewModels<MypageViewModel>()
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_ddoring)

        viewModel.getMemberInfo()

        viewModel.memberInfo.observe(this) {
            Glide.with(this)
                .load(it.photo)
                .into(binding.circleImageView)
            binding.editText.text = it.name
        }

        binding.circleImageView.setOnClickListener {
            pickImageFromGallery()
        }

        binding.editCompleteBtn.setOnClickListener {
            viewModel.updateProfile(convertUriToPart(imageUri!!))
            finish()
        }
    }

    private val pickImageContract =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                Glide.with(this)
                    .load(it)
                    .into(binding.circleImageView)
                imageUri = uri
            }
        }

    private fun pickImageFromGallery() {
        pickImageContract.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    fun convertUriToPart(uri: Uri): MultipartBody.Part {
        val inputStream = contentResolver.openInputStream(uri)
        var body: MultipartBody.Part? = null
        inputStream?.let {
            val requestBody = it.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
            body = MultipartBody.Part.createFormData("photo", "profile", requestBody)
        }
        return body ?: throw Exception("Image is null")
    }
}