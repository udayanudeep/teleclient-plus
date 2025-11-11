package com.telegramplus.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.telegramplus.app.data.model.TransactionType
import com.telegramplus.app.data.model.WalletTransaction
import com.telegramplus.app.databinding.ItemTransactionBinding
import com.telegramplus.app.util.DateTimeUtils
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

class TransactionAdapter : ListAdapter<WalletTransaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class TransactionViewHolder(
        private val binding: ItemTransactionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(transaction: WalletTransaction) {
            binding.apply {
                tvTransactionTitle.text = transaction.title
                tvTransactionSubtitle.text = transaction.subtitle
                tvTransactionTime.text = DateTimeUtils.formatMessageTime(transaction.timestamp)
                tvTransactionAmount.text = formatAmount(transaction)
            }
        }
        
        private fun formatAmount(transaction: WalletTransaction): String {
            val format = NumberFormat.getCurrencyInstance(Locale.US)
            format.currency = java.util.Currency.getInstance(transaction.currency)
            val amount = if (transaction.type == TransactionType.DEBIT) "-" + format.format(transaction.amount.absoluteValue()) else "+" + format.format(transaction.amount.absoluteValue())
            return amount
        }
    }
    
    class TransactionDiffCallback : DiffUtil.ItemCallback<WalletTransaction>() {
        override fun areItemsTheSame(oldItem: WalletTransaction, newItem: WalletTransaction): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: WalletTransaction, newItem: WalletTransaction): Boolean = oldItem == newItem
    }
}

private fun Double.absoluteValue(): Double = abs(this)
