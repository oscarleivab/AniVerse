package com.example.aniverse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aniverse.databinding.FragmentLoginBinding
import com.example.aniverse.databinding.FragmentNoticeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator

class NoticeFragment : Fragment() {
    private var _binding : FragmentNoticeBinding? = null
    private val binding get() = _binding!!
    private var nombreUsuario: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nombreUsuario = arguments?.getString("Nombre")

        binding.vpNotice.adapter = NoticeAdapter(this, nombreUsuario)
        TabLayoutMediator(binding.TabNotice,binding.vpNotice){
            tab, position ->
                when(position){
                    0 -> {
                        tab.text = getString(R.string.noticeIni)
                        tab.setIcon(R.drawable.baseline_home_24)
                        val badge : BadgeDrawable = tab.orCreateBadge
                        badge.backgroundColor =
                            ContextCompat.getColor(
                                requireContext().applicationContext,R.color.puple)
                        badge.maxCharacterCount = 3
                        badge.badgeGravity = BadgeDrawable.TOP_START
                    }
                    1 -> {
                        tab.text = getString(R.string.noticeMenu)
                        tab.setIcon(R.drawable.baseline_menu_24)
                    }
                }
        }.attach()
    }
}
class NoticeAdapter(fragment: Fragment, private val nombreUsuario: String?) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =
            if (position==0)
                Tab1NoticeFragment().apply {
                    arguments = Bundle().apply {
                        putString("Nombre", nombreUsuario)
                    }
                }
            else
                Tab2NoticeFragment()
        return fragment
    }
}