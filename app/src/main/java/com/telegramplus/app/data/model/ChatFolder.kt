package com.telegramplus.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "folders")
@Parcelize
data class ChatFolder(
    @PrimaryKey
    val id: String,
    val name: String,
    val iconName: String,
    val color: Int,
    val position: Int = 0,
    val includePinned: Boolean = true,
    val includeMuted: Boolean = true,
    val includeRead: Boolean = true,
    val includeArchived: Boolean = false,
    val chatIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable
