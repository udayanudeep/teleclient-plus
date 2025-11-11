package com.telegramplus.app.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.telegramplus.app.TelegramPlusApp
import com.telegramplus.app.databinding.ActivitySettingsBinding
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySettingsBinding
    private val preferencesManager by lazy { TelegramPlusApp.instance.preferencesManager }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        loadSettings()
    }
    
    private fun setupToolbar() {
        // TODO: Setup toolbar
    }
    
    private fun loadSettings() {
        lifecycleScope.launch {
            // Observe settings
            preferencesManager.ghostModeEnabled.collect { enabled ->
                // TODO: Update UI
            }
        }
    }
}
