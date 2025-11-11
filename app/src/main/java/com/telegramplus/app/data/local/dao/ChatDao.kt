package com.telegramplus.app.data.local.dao

import androidx.room.*
import com.telegramplus.app.data.model.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    
    @Query("SELECT * FROM chats ORDER BY isPinned DESC, lastMessageTime DESC")
    fun getAllChats(): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE id = :chatId")
    suspend fun getChatById(chatId: String): Chat?
    
    @Query("SELECT * FROM chats WHERE id = :chatId")
    fun getChatByIdFlow(chatId: String): Flow<Chat?>
    
    @Query("SELECT * FROM chats WHERE isPinned = 1 ORDER BY lastMessageTime DESC")
    fun getPinnedChats(): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE isArchived = 1 ORDER BY lastMessageTime DESC")
    fun getArchivedChats(): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE folderId = :folderId ORDER BY lastMessageTime DESC")
    fun getChatsByFolder(folderId: String): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE unreadCount > 0 ORDER BY lastMessageTime DESC")
    fun getUnreadChats(): Flow<List<Chat>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: Chat)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<Chat>)
    
    @Update
    suspend fun updateChat(chat: Chat)
    
    @Delete
    suspend fun deleteChat(chat: Chat)
    
    @Query("DELETE FROM chats WHERE id = :chatId")
    suspend fun deleteChatById(chatId: String)
    
    @Query("UPDATE chats SET unreadCount = 0 WHERE id = :chatId")
    suspend fun markChatAsRead(chatId: String)
    
    @Query("UPDATE chats SET isPinned = :isPinned WHERE id = :chatId")
    suspend fun toggleChatPin(chatId: String, isPinned: Boolean)
    
    @Query("UPDATE chats SET isMuted = :isMuted WHERE id = :chatId")
    suspend fun toggleChatMute(chatId: String, isMuted: Boolean)
    
    @Query("UPDATE chats SET isArchived = :isArchived WHERE id = :chatId")
    suspend fun toggleChatArchive(chatId: String, isArchived: Boolean)
    
    @Query("SELECT * FROM chats WHERE title LIKE :query ORDER BY lastMessageTime DESC")
    fun searchChats(query: String): Flow<List<Chat>>
}
