package com.telegramplus.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telegramplus.app.data.model.ServiceShortcut
import com.telegramplus.app.databinding.ItemServiceShortcutBinding

class ServiceShortcutAdapter(
    private val onShortcutClick: (ServiceShortcut) -> Unit
) : ListAdapter<ServiceShortcut, ServiceShortcutAdapter.ServiceShortcutViewHolder>(ServiceShortcutDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceShortcutViewHolder {
        val binding = ItemServiceShortcutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceShortcutViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ServiceShortcutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ServiceShortcutViewHolder(
        private val binding: ItemServiceShortcutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shortcut: ServiceShortcut) {
            binding.apply {
                ivServiceIcon.setImageResource(shortcut.iconResId)
                tvServiceTitle.text = shortcut.title
                tvServiceSubtitle.text = shortcut.subtitle
                root.setOnClickListener { onShortcutClick(shortcut) }
            }
        }
    }
    
    class ServiceShortcutDiffCallback : DiffUtil.ItemCallback<ServiceShortcut>() {
        override fun areItemsTheSame(oldItem: ServiceShortcut, newItem: ServiceShortcut): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ServiceShortcut, newItem: ServiceShortcut): Boolean = oldItem == newItem
    }
}
