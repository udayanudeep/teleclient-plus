package com.telegramplus.app.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.telegramplus.app.ui.fragments.CallsFragment
import com.telegramplus.app.ui.fragments.ChatsFragment
import com.telegramplus.app.ui.fragments.ContactsFragment
import com.telegramplus.app.ui.fragments.SuperAppFragment

class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    
    override fun getItemCount(): Int = 4
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChatsFragment()
            1 -> ContactsFragment()
            2 -> CallsFragment()
            3 -> SuperAppFragment()
            else -> ChatsFragment()
        }
    }
}
