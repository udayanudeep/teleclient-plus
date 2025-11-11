package com.telegramplus.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telegramplus.app.data.model.Reward
import com.telegramplus.app.databinding.ItemRewardBinding

class RewardAdapter(
    private val onRewardClick: (Reward) -> Unit
) : ListAdapter<Reward, RewardAdapter.RewardViewHolder>(RewardDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RewardViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class RewardViewHolder(
        private val binding: ItemRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reward: Reward) {
            binding.apply {
                ivRewardIcon.setImageResource(reward.iconResId)
                tvRewardTitle.text = reward.title
                tvRewardDescription.text = reward.description
                tvRewardPoints.text = "${reward.pointsRequired} pts"
                root.setOnClickListener { onRewardClick(reward) }
            }
        }
    }
    
    class RewardDiffCallback : DiffUtil.ItemCallback<Reward>() {
        override fun areItemsTheSame(oldItem: Reward, newItem: Reward): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Reward, newItem: Reward): Boolean = oldItem == newItem
    }
}
