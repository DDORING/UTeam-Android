package com.neordi6th.ddoring


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neordi6th.ddoring.databinding.FragmentLogin2Binding


class Login2Fragment: Fragment() {

    lateinit var binding: FragmentLogin2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogin2Binding.inflate(inflater, container, false)

        binding.apply {
            nextBtn2.setOnClickListener {
                findNavController().navigate(R.id.action_login2Fragment_to_addProfileImageFragment)
            }
        }
        val recyclerView = binding.loginRcview

        val dataset = arrayListOf<CharacterData>()
        val data1 = CharacterData(1, "다정한 연예인", "얼른 하고 나랑 놀자~")
        val data2 = CharacterData(1, "무서운 교수님", "얼른 하고 나랑 놀자~")
        val data3 = CharacterData(1, "냉정한 친구", "얼른 하고 나랑 놀자~")

        dataset.add(data1)
        dataset.add(data2)
        dataset.add(data3)

        val adapter = CharacterAdapter(dataset)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }



}