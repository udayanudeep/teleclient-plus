package com.telegramplus.app.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.telegramplus.app.R
import com.telegramplus.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupViewPager()
        setupFab()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }
    
    private fun setupViewPager() {
        val adapter = MainPagerAdapter(this)
        binding.viewPager.adapter = adapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.chats)
                1 -> getString(R.string.contacts)
                2 -> getString(R.string.calls)
                3 -> getString(R.string.super_app)
                else -> ""
            }
        }.attach()
        
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateFabIcon(position)
            }
        })
    }
    
    private fun setupFab() {
        binding.fabNewChat.setOnClickListener {
            when (binding.viewPager.currentItem) {
                0 -> openNewChat()
                1 -> openNewContact()
                2 -> startNewCall()
                3 -> openSuperAppHub()
            }
        }
    }
    
    private fun updateFabIcon(position: Int) {
        val iconRes = when (position) {
            0 -> R.drawable.ic_edit
            1 -> R.drawable.ic_edit
            2 -> R.drawable.ic_call
            3 -> R.drawable.ic_super_app
            else -> R.drawable.ic_edit
        }
        binding.fabNewChat.setImageResource(iconRes)
    }
    
    private fun openNewChat() {
        // TODO: Implement new chat
    }
    
    private fun openNewContact() {
        // TODO: Implement add contact
    }
    
    private fun startNewCall() {
        // TODO: Implement new call
    }
    
    private fun openSuperAppHub() {
        // TODO: Launch quick actions or AI assistant
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // TODO: Implement search
                true
            }
            R.id.action_settings -> {
                // TODO: Open settings
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
