package com.telegramplus.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telegramplus.app.data.model.MiniApp
import com.telegramplus.app.databinding.ItemMiniAppBinding

class MiniAppAdapter(
    private val onMiniAppClick: (MiniApp) -> Unit
) : ListAdapter<MiniApp, MiniAppAdapter.MiniAppViewHolder>(MiniAppDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniAppViewHolder {
        val binding = ItemMiniAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MiniAppViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: MiniAppViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class MiniAppViewHolder(
        private val binding: ItemMiniAppBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(miniApp: MiniApp) {
            binding.apply {
                ivMiniAppIcon.setImageResource(miniApp.iconResId)
                tvMiniAppTitle.text = miniApp.name
                tvMiniAppDescription.text = miniApp.description
                root.setOnClickListener { onMiniAppClick(miniApp) }
            }
        }
    }
    
    class MiniAppDiffCallback : DiffUtil.ItemCallback<MiniApp>() {
        override fun areItemsTheSame(oldItem: MiniApp, newItem: MiniApp): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MiniApp, newItem: MiniApp): Boolean = oldItem == newItem
    }
}
