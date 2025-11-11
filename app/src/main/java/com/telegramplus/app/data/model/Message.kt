package com.telegramplus.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(tableName = "messages")
@Parcelize
data class Message(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val senderId: String,
    val text: String? = null,
    val messageType: MessageType,
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val isEdited: Boolean = false,
    val editedAt: Long? = null,
    val isDeleted: Boolean = false,
    val deletedAt: Long? = null,
    val replyToMessageId: String? = null,
    val forwardedFromChatId: String? = null,
    val forwardedFromMessageId: String? = null,
    val mediaUrl: String? = null,
    val thumbnailUrl: String? = null,
    val fileName: String? = null,
    val fileSize: Long? = null,
    val duration: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val contactName: String? = null,
    val contactPhone: String? = null,
    val scheduledAt: Long? = null,
    val isSent: Boolean = true,
    val isDelivered: Boolean = false
) : Parcelable

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    VOICE,
    DOCUMENT,
    STICKER,
    GIF,
    LOCATION,
    CONTACT,
    POLL
}
