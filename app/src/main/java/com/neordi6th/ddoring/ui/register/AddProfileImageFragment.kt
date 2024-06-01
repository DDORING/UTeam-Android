package com.neordi6th.ddoring.ui.register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.neordi6th.ddoring.databinding.FragmentAddProfileImageBinding
import com.neordi6th.ddoring.ui.home.HomeActivity
import com.neordi6th.ddoring.ui.login.LoginViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class AddProfileImageFragment : Fragment() {

    private lateinit var binding: FragmentAddProfileImageBinding
    private val viewModel by viewModels<LoginViewModel>()
    private var imageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddProfileImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // 서버에 등록이 되면 홈화면으로 이동
            nextBtn.setOnClickListener {
                imageUri?.let { it1 -> convertUriToPart(it1) }?.let { it2 ->
                    viewModel.uploadCharacter(binding.inputName.text.toString(),
                        it2
                    )
                }
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
            // 갤러리에서 사진을 받아와야함
            imageView.setOnClickListener {
                pickImageFromGallery()
            }
        }
    }

    private val pickImageContract =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                Glide.with(this)
                    .load(it)
                    .into(binding.imageView)
                imageUri = uri
            }
        }

    private fun pickImageFromGallery() {
        pickImageContract.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    fun convertUriToPart(uri: Uri): MultipartBody.Part {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        var body: MultipartBody.Part? = null
        inputStream?.let {
            val requestBody = it.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
            body = MultipartBody.Part.createFormData("photo", "profile", requestBody)
        }
        return body ?: throw Exception("Image is null")
    }

}