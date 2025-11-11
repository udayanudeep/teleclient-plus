package com.telegramplus.app.ui.chat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.telegramplus.app.R
import com.telegramplus.app.TelegramPlusApp
import com.telegramplus.app.databinding.ActivityChatBinding
import com.telegramplus.app.ui.adapters.MessageAdapter
import com.telegramplus.app.ui.viewmodel.ChatViewModel
import com.telegramplus.app.data.repository.MessageRepository
import com.telegramplus.app.data.repository.UserRepository
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var messageAdapter: MessageAdapter
    
    private val chatId by lazy { intent.getStringExtra(EXTRA_CHAT_ID) ?: "" }
    private val chatName by lazy { intent.getStringExtra(EXTRA_CHAT_NAME) ?: "" }
    private val currentUserId = "current_user_id" // TODO: Get from preferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViewModel()
        setupToolbar()
        setupRecyclerView()
        setupMessageInput()
        observeMessages()
    }
    
    private fun setupViewModel() {
        val database = TelegramPlusApp.instance.database
        val messageRepository = MessageRepository(database.messageDao())
        val userRepository = UserRepository(database.userDao())
        viewModel = ChatViewModel(messageRepository, userRepository, chatId, currentUserId)
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tvUserName.text = chatName
        binding.tvUserStatus.text = "online" // TODO: Get real status
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(currentUserId)
        
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity).apply {
                reverseLayout = true
                stackFromEnd = true
            }
            adapter = messageAdapter
        }
    }
    
    private fun setupMessageInput() {
        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            if (text.isNotBlank()) {
                viewModel.sendMessage(text)
                binding.etMessage.setText("")
            }
        }
        
        binding.btnAttach.setOnClickListener {
            // TODO: Show attachment options
        }
    }
    
    private fun observeMessages() {
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                messageAdapter.submitList(messages)
                if (messages.isNotEmpty()) {
                    binding.rvMessages.scrollToPosition(0)
                }
            }
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_call -> {
                // TODO: Start voice call
                true
            }
            R.id.action_video_call -> {
                // TODO: Start video call
                true
            }
            R.id.action_mute -> {
                // TODO: Toggle mute
                true
            }
            R.id.action_search -> {
                // TODO: Search in chat
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    companion object {
        const val EXTRA_CHAT_ID = "chat_id"
        const val EXTRA_CHAT_NAME = "chat_name"
    }
}
