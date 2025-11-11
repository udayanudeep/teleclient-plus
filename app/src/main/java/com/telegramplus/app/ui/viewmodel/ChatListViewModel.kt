package com.telegramplus.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telegramplus.app.data.model.Chat
import com.telegramplus.app.data.repository.ChatRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    
    val chats: StateFlow<List<Chat>> = searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                chatRepository.getAllChats()
            } else {
                chatRepository.searchChats(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val pinnedChats: StateFlow<List<Chat>> = chatRepository.getPinnedChats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val unreadChats: StateFlow<List<Chat>> = chatRepository.getUnreadChats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun toggleChatPin(chatId: String, isPinned: Boolean) {
        viewModelScope.launch {
            chatRepository.toggleChatPin(chatId, !isPinned)
        }
    }
    
    fun toggleChatMute(chatId: String, isMuted: Boolean) {
        viewModelScope.launch {
            chatRepository.toggleChatMute(chatId, !isMuted)
        }
    }
    
    fun toggleChatArchive(chatId: String, isArchived: Boolean) {
        viewModelScope.launch {
            chatRepository.toggleChatArchive(chatId, !isArchived)
        }
    }
    
    fun markChatAsRead(chatId: String) {
        viewModelScope.launch {
            chatRepository.markChatAsRead(chatId)
        }
    }
    
    fun deleteChat(chat: Chat) {
        viewModelScope.launch {
            chatRepository.deleteChat(chat)
        }
    }
}
