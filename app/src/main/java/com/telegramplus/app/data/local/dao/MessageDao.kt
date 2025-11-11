package com.telegramplus.app.data.local.dao

import androidx.room.*
import com.telegramplus.app.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId AND isDeleted = 0 ORDER BY timestamp DESC")
    fun getMessagesByChatId(chatId: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId AND isDeleted = 0 ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getMessagesByChatIdPaginated(chatId: String, limit: Int, offset: Int): List<Message>
    
    @Query("SELECT * FROM messages WHERE id = :messageId")
    suspend fun getMessageById(messageId: String): Message?
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId AND isRead = 0 AND senderId != :currentUserId")
    fun getUnreadMessages(chatId: String, currentUserId: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE scheduledAt > :currentTime ORDER BY scheduledAt ASC")
    fun getScheduledMessages(currentTime: Long): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE isDeleted = 1 ORDER BY deletedAt DESC")
    fun getDeletedMessages(): Flow<List<Message>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<Message>)
    
    @Update
    suspend fun updateMessage(message: Message)
    
    @Delete
    suspend fun deleteMessage(message: Message)
    
    @Query("DELETE FROM messages WHERE id = :messageId")
    suspend fun deleteMessageById(messageId: String)
    
    @Query("UPDATE messages SET isRead = 1 WHERE chatId = :chatId AND senderId != :currentUserId")
    suspend fun markMessagesAsRead(chatId: String, currentUserId: String)
    
    @Query("UPDATE messages SET isDeleted = 1, deletedAt = :deletedAt WHERE id = :messageId")
    suspend fun softDeleteMessage(messageId: String, deletedAt: Long)
    
    @Query("UPDATE messages SET text = :newText, isEdited = 1, editedAt = :editedAt WHERE id = :messageId")
    suspend fun editMessage(messageId: String, newText: String, editedAt: Long)
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId AND (text LIKE :query) ORDER BY timestamp DESC")
    fun searchMessagesInChat(chatId: String, query: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE text LIKE :query ORDER BY timestamp DESC")
    fun searchAllMessages(query: String): Flow<List<Message>>
}
