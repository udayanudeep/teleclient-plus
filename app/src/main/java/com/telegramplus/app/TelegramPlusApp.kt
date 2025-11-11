package com.telegramplus.app

import android.app.Application
import com.telegramplus.app.data.local.TelegramDatabase
import com.telegramplus.app.util.PreferencesManager

class TelegramPlusApp : Application() {
    
    lateinit var database: TelegramDatabase
        private set
    
    lateinit var preferencesManager: PreferencesManager
        private set
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        database = TelegramDatabase.getDatabase(this)
        preferencesManager = PreferencesManager(this)
    }
    
    companion object {
        lateinit var instance: TelegramPlusApp
            private set
    }
}
