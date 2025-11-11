package com.telegramplus.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.telegramplus.app.TelegramPlusApp
import com.telegramplus.app.databinding.FragmentChatsBinding
import com.telegramplus.app.ui.adapters.ChatAdapter
import com.telegramplus.app.ui.viewmodel.ChatListViewModel
import com.telegramplus.app.data.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatsFragment : Fragment() {
    
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: ChatListViewModel
    private lateinit var chatAdapter: ChatAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupRecyclerView()
        observeChats()
    }
    
    private fun setupViewModel() {
        val database = TelegramPlusApp.instance.database
        val chatRepository = ChatRepository(database.chatDao())
        viewModel = ChatListViewModel(chatRepository)
    }
    
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter { chat ->
            // TODO: Open chat
        }
        
        binding.rvChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }
    
    private fun observeChats() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.chats.collect { chats ->
                if (chats.isEmpty()) {
                    binding.tvEmptyState.visibility = View.VISIBLE
                    binding.rvChats.visibility = View.GONE
                } else {
                    binding.tvEmptyState.visibility = View.GONE
                    binding.rvChats.visibility = View.VISIBLE
                    chatAdapter.submitList(chats)
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
