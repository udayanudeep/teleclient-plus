package com.telegramplus.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.telegramplus.app.data.local.dao.*
import com.telegramplus.app.data.model.*

@Database(
    entities = [
        User::class,
        Chat::class,
        Message::class,
        ChatFolder::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TelegramDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun chatFolderDao(): ChatFolderDao
    
    companion object {
        @Volatile
        private var INSTANCE: TelegramDatabase? = null
        
        fun getDatabase(context: Context): TelegramDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TelegramDatabase::class.java,
                    "telegram_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
