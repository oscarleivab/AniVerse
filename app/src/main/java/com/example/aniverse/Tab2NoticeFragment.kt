package com.example.aniverse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aniverse.databinding.FragmentLoginBinding
import com.example.aniverse.databinding.FragmentTab2NoticeBinding

class Tab2NoticeFragment : Fragment() {
    private var _binding : FragmentTab2NoticeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTab2NoticeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTab2.setOnClickListener{
           findNavController().navigate(R.id.action_noticeFragment_to_creditFragment)
        }
        binding.btnExit.setOnClickListener{
            findNavController().navigate(R.id.action_noticeFragment_to_loginFragment)
        }
    }
}