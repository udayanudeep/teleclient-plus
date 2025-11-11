package com.telegramplus.app.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telegramplus.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // TODO: Implement login flow
    }
}
