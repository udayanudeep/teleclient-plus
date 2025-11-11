package com.telegramplus.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telegramplus.app.data.model.Message
import com.telegramplus.app.data.model.User
import com.telegramplus.app.data.repository.MessageRepository
import com.telegramplus.app.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
    private val chatId: String,
    private val currentUserId: String
) : ViewModel() {
    
    val messages: StateFlow<List<Message>> = messageRepository.getMessagesByChatId(chatId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()
    
    private val _isTyping = MutableStateFlow(false)
    val isTyping = _isTyping.asStateFlow()
    
    fun updateMessageText(text: String) {
        _messageText.value = text
        _isTyping.value = text.isNotEmpty()
    }
    
    fun sendMessage(text: String) {
        if (text.isBlank()) return
        
        viewModelScope.launch {
            val message = Message(
                id = generateMessageId(),
                chatId = chatId,
                senderId = currentUserId,
                text = text,
                messageType = com.telegramplus.app.data.model.MessageType.TEXT,
                timestamp = System.currentTimeMillis(),
                isSent = true,
                isDelivered = true
            )
            messageRepository.insertMessage(message)
            _messageText.value = ""
            _isTyping.value = false
        }
    }
    
    fun editMessage(messageId: String, newText: String) {
        viewModelScope.launch {
            messageRepository.editMessage(messageId, newText)
        }
    }
    
    fun deleteMessage(messageId: String) {
        viewModelScope.launch {
            messageRepository.softDeleteMessage(messageId)
        }
    }
    
    fun markMessagesAsRead() {
        viewModelScope.launch {
            messageRepository.markMessagesAsRead(chatId, currentUserId)
        }
    }
    
    private fun generateMessageId(): String {
        return "msg_${System.currentTimeMillis()}_${(0..9999).random()}"
    }
}
