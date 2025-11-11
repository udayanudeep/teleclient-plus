package com.telegramplus.app.ui.media

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telegramplus.app.databinding.ActivityMediaViewerBinding

class MediaViewerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMediaViewerBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // TODO: Implement media viewer
    }
}
