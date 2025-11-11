package com.telegramplus.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey
    val id: String,
    val username: String,
    val firstName: String,
    val lastName: String? = null,
    val phoneNumber: String,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: Long? = null,
    val isPremium: Boolean = false,
    val isVerified: Boolean = false,
    val isBot: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable {
    fun getFullName(): String {
        return if (lastName != null) "$firstName $lastName" else firstName
    }
    
    fun getDisplayName(): String {
        return username.ifEmpty { getFullName() }
    }
}
