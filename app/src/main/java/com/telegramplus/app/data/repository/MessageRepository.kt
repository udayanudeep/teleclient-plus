package com.telegramplus.app.data.repository

import com.telegramplus.app.data.local.dao.MessageDao
import com.telegramplus.app.data.model.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    
    fun getMessagesByChatId(chatId: String): Flow<List<Message>> = messageDao.getMessagesByChatId(chatId)
    
    suspend fun getMessagesByChatIdPaginated(chatId: String, limit: Int, offset: Int): List<Message> {
        return messageDao.getMessagesByChatIdPaginated(chatId, limit, offset)
    }
    
    suspend fun getMessageById(messageId: String): Message? = messageDao.getMessageById(messageId)
    
    fun getUnreadMessages(chatId: String, currentUserId: String): Flow<List<Message>> {
        return messageDao.getUnreadMessages(chatId, currentUserId)
    }
    
    fun getScheduledMessages(currentTime: Long): Flow<List<Message>> {
        return messageDao.getScheduledMessages(currentTime)
    }
    
    fun getDeletedMessages(): Flow<List<Message>> = messageDao.getDeletedMessages()
    
    fun searchMessagesInChat(chatId: String, query: String): Flow<List<Message>> {
        return messageDao.searchMessagesInChat(chatId, "%$query%")
    }
    
    fun searchAllMessages(query: String): Flow<List<Message>> {
        return messageDao.searchAllMessages("%$query%")
    }
    
    suspend fun insertMessage(message: Message): Long = messageDao.insertMessage(message)
    
    suspend fun insertMessages(messages: List<Message>) = messageDao.insertMessages(messages)
    
    suspend fun updateMessage(message: Message) = messageDao.updateMessage(message)
    
    suspend fun deleteMessage(message: Message) = messageDao.deleteMessage(message)
    
    suspend fun markMessagesAsRead(chatId: String, currentUserId: String) {
        messageDao.markMessagesAsRead(chatId, currentUserId)
    }
    
    suspend fun softDeleteMessage(messageId: String, deletedAt: Long = System.currentTimeMillis()) {
        messageDao.softDeleteMessage(messageId, deletedAt)
    }
    
    suspend fun editMessage(messageId: String, newText: String, editedAt: Long = System.currentTimeMillis()) {
        messageDao.editMessage(messageId, newText, editedAt)
    }
}
