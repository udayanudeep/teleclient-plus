package com.telegramplus.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.telegramplus.app.R
import com.telegramplus.app.data.model.Chat
import com.telegramplus.app.databinding.ItemChatBinding
import com.telegramplus.app.util.DateTimeUtils

class ChatAdapter(
    private val onChatClick: (Chat) -> Unit
) : ListAdapter<Chat, ChatAdapter.ChatViewHolder>(ChatDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ChatViewHolder(
        private val binding: ItemChatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(chat: Chat) {
            binding.apply {
                tvName.text = chat.title
                tvLastMessage.text = chat.lastMessageText ?: "No messages yet"
                tvTime.text = chat.lastMessageTime?.let { 
                    DateTimeUtils.formatChatListTime(it) 
                } ?: ""
                
                // Load avatar
                ivAvatar.load(chat.avatarUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_edit)
                    transformations(CircleCropTransformation())
                }
                
                // Unread count
                tvUnreadCount.isVisible = chat.unreadCount > 0
                tvUnreadCount.text = if (chat.unreadCount > 99) "99+" else chat.unreadCount.toString()
                
                // Online indicator (for private chats)
                onlineIndicator.isVisible = false // TODO: Check user online status
                
                root.setOnClickListener {
                    onChatClick(chat)
                }
            }
        }
    }
    
    class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }
}
