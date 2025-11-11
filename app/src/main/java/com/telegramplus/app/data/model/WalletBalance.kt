package com.telegramplus.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletBalance(
    val totalBalance: Double,
    val currency: String,
    val loyaltyPoints: Int,
    val tier: LoyaltyTier
) : Parcelable

enum class LoyaltyTier {
    SILVER,
    GOLD,
    PLATINUM
}
