package com.telegramplus.app.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class WalletTransaction(
    val id: String,
    val title: String,
    val subtitle: String,
    val amount: Double,
    val currency: String,
    val timestamp: Long,
    val type: TransactionType
) : Parcelable

enum class TransactionType {
    CREDIT,
    DEBIT,
    REWARD
}
