package com.telegramplus.app.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtils {
    
    fun formatMessageTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
        
        val today = Calendar.getInstance()
        
        return when {
            isSameDay(calendar, today) -> {
                // Today - show time
                SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(timestamp))
            }
            isYesterday(calendar, today) -> {
                "Yesterday"
            }
            diff < TimeUnit.DAYS.toMillis(7) -> {
                // This week - show day name
                SimpleDateFormat("EEEE", Locale.getDefault()).format(Date(timestamp))
            }
            else -> {
                // Older - show date
                SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(Date(timestamp))
            }
        }
    }
    
    fun formatChatListTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
        }
        
        val today = Calendar.getInstance()
        
        return when {
            isSameDay(calendar, today) -> {
                SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(timestamp))
            }
            isYesterday(calendar, today) -> {
                "Yesterday"
            }
            diff < TimeUnit.DAYS.toMillis(7) -> {
                SimpleDateFormat("EEE", Locale.getDefault()).format(Date(timestamp))
            }
            else -> {
                SimpleDateFormat("MMM d", Locale.getDefault()).format(Date(timestamp))
            }
        }
    }
    
    fun formatLastSeen(lastSeen: Long?): String {
        if (lastSeen == null) return "long time ago"
        
        val now = System.currentTimeMillis()
        val diff = now - lastSeen
        
        return when {
            diff < TimeUnit.MINUTES.toMillis(1) -> "just now"
            diff < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
                "$minutes minute${if (minutes > 1) "s" else ""} ago"
            }
            diff < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(diff)
                "$hours hour${if (hours > 1) "s" else ""} ago"
            }
            diff < TimeUnit.DAYS.toMillis(7) -> {
                SimpleDateFormat("EEEE 'at' h:mm a", Locale.getDefault()).format(Date(lastSeen))
            }
            else -> {
                SimpleDateFormat("MMM d 'at' h:mm a", Locale.getDefault()).format(Date(lastSeen))
            }
        }
    }
    
    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }
    
    private fun isYesterday(cal: Calendar, today: Calendar): Boolean {
        val yesterday = today.clone() as Calendar
        yesterday.add(Calendar.DAY_OF_YEAR, -1)
        return isSameDay(cal, yesterday)
    }
}
