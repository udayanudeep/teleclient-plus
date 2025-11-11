package com.telegramplus.app.data.model

import androidx.annotation.DrawableRes

data class ServiceShortcut(
    val id: String,
    val title: String,
    val subtitle: String,
    @DrawableRes val iconResId: Int,
    val type: ServiceType
)

enum class ServiceType {
    FINANCE,
    BILL_PAYMENT,
    RIDE_HAILING,
    FOOD_DELIVERY,
    SHOPPING,
    TRAVEL,
    UTILITIES,
    HEALTHCARE,
    GOVERNMENT
}
