package com.telegramplus.app.data.repository

import com.telegramplus.app.data.local.dao.ChatDao
import com.telegramplus.app.data.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val chatDao: ChatDao
) {
    
    fun getAllChats(): Flow<List<Chat>> = chatDao.getAllChats()
    
    fun getChatById(chatId: String): Flow<Chat?> = chatDao.getChatByIdFlow(chatId)
    
    fun getPinnedChats(): Flow<List<Chat>> = chatDao.getPinnedChats()
    
    fun getArchivedChats(): Flow<List<Chat>> = chatDao.getArchivedChats()
    
    fun getChatsByFolder(folderId: String): Flow<List<Chat>> = chatDao.getChatsByFolder(folderId)
    
    fun getUnreadChats(): Flow<List<Chat>> = chatDao.getUnreadChats()
    
    fun searchChats(query: String): Flow<List<Chat>> = chatDao.searchChats("%$query%")
    
    suspend fun insertChat(chat: Chat) = chatDao.insertChat(chat)
    
    suspend fun insertChats(chats: List<Chat>) = chatDao.insertChats(chats)
    
    suspend fun updateChat(chat: Chat) = chatDao.updateChat(chat)
    
    suspend fun deleteChat(chat: Chat) = chatDao.deleteChat(chat)
    
    suspend fun markChatAsRead(chatId: String) = chatDao.markChatAsRead(chatId)
    
    suspend fun toggleChatPin(chatId: String, isPinned: Boolean) = chatDao.toggleChatPin(chatId, isPinned)
    
    suspend fun toggleChatMute(chatId: String, isMuted: Boolean) = chatDao.toggleChatMute(chatId, isMuted)
    
    suspend fun toggleChatArchive(chatId: String, isArchived: Boolean) = chatDao.toggleChatArchive(chatId, isArchived)
}
