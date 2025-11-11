package com.telegramplus.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "chats")
@Parcelize
data class Chat(
    @PrimaryKey
    val id: String,
    val type: ChatType,
    val title: String,
    val avatarUrl: String? = null,
    val lastMessageId: String? = null,
    val lastMessageText: String? = null,
    val lastMessageTime: Long? = null,
    val unreadCount: Int = 0,
    val isPinned: Boolean = false,
    val isMuted: Boolean = false,
    val isArchived: Boolean = false,
    val folderId: String? = null,
    val membersCount: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

enum class ChatType {
    PRIVATE,
    GROUP,
    CHANNEL,
    SAVED_MESSAGES
}
