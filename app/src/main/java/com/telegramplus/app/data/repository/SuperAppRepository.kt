package com.telegramplus.app.data.repository

import com.telegramplus.app.R
import com.telegramplus.app.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuperAppRepository @Inject constructor() {
    
    private val walletBalanceFlow = MutableStateFlow(
        WalletBalance(
            totalBalance = 245.75,
            currency = "USD",
            loyaltyPoints = 1280,
            tier = LoyaltyTier.GOLD
        )
    )
    
    private val transactions = listOf(
        WalletTransaction(
            id = "tx_001",
            title = "Coffee Shop",
            subtitle = "Latte and croissant",
            amount = 8.5,
            currency = "USD",
            timestamp = System.currentTimeMillis() - 3_600_000,
            type = TransactionType.DEBIT
        ),
        WalletTransaction(
            id = "tx_002",
            title = "Ride Hailing",
            subtitle = "Downtown to Airport",
            amount = 18.75,
            currency = "USD",
            timestamp = System.currentTimeMillis() - 21_600_000,
            type = TransactionType.DEBIT
        ),
        WalletTransaction(
            id = "tx_003",
            title = "Reward Points",
            subtitle = "Weekly streak bonus",
            amount = 5.0,
            currency = "USD",
            timestamp = System.currentTimeMillis() - 28_800_000,
            type = TransactionType.REWARD
        ),
        WalletTransaction(
            id = "tx_004",
            title = "Payment Received",
            subtitle = "From Alex Johnson",
            amount = 42.0,
            currency = "USD",
            timestamp = System.currentTimeMillis() - 86_400_000,
            type = TransactionType.CREDIT
        )
    )
    
    private val miniApps = listOf(
        MiniApp(
            id = "mini_wallet_insights",
            name = "Wallet Insights",
            description = "Track spending smarter",
            iconResId = R.drawable.ic_wallet,
            deepLink = "telegramplus://mini-app/wallet-insights",
            category = MiniAppCategory.FINANCE
        ),
        MiniApp(
            id = "mini_food_delivery",
            name = "Quick Bites",
            description = "Food delivery in 15 mins",
            iconResId = R.drawable.ic_food,
            deepLink = "telegramplus://mini-app/quick-bites",
            category = MiniAppCategory.LIFESTYLE
        ),
        MiniApp(
            id = "mini_ride_hailing",
            name = "Ride Now",
            description = "Book rides instantly",
            iconResId = R.drawable.ic_ride,
            deepLink = "telegramplus://mini-app/ride-now",
            category = MiniAppCategory.TRAVEL
        ),
        MiniApp(
            id = "mini_travel",
            name = "FlyAway",
            description = "Book flights worldwide",
            iconResId = R.drawable.ic_travel,
            deepLink = "telegramplus://mini-app/flyaway",
            category = MiniAppCategory.TRAVEL
        ),
        MiniApp(
            id = "mini_marketplace",
            name = "ShopSquare",
            description = "Trendy marketplace",
            iconResId = R.drawable.ic_shop,
            deepLink = "telegramplus://mini-app/shopsquare",
            category = MiniAppCategory.ENTERTAINMENT
        ),
        MiniApp(
            id = "mini_ai_assistant",
            name = "Nova AI",
            description = "Smart assistant for anything",
            iconResId = R.drawable.ic_ai,
            deepLink = "telegramplus://mini-app/nova-ai",
            category = MiniAppCategory.PRODUCTIVITY
        )
    )
    
    private val serviceShortcuts = listOf(
        ServiceShortcut(
            id = "shortcut_scan_pay",
            title = "Scan & Pay",
            subtitle = "Instant QR payments",
            iconResId = R.drawable.ic_scan_pay,
            type = ServiceType.BILL_PAYMENT
        ),
        ServiceShortcut(
            id = "shortcut_send_money",
            title = "Send Money",
            subtitle = "Zero fee transfers",
            iconResId = R.drawable.ic_send_money,
            type = ServiceType.FINANCE
        ),
        ServiceShortcut(
            id = "shortcut_add_money",
            title = "Add Money",
            subtitle = "Top up via bank",
            iconResId = R.drawable.ic_add_money,
            type = ServiceType.FINANCE
        ),
        ServiceShortcut(
            id = "shortcut_pay_bills",
            title = "Pay Bills",
            subtitle = "Utilities & recharge",
            iconResId = R.drawable.ic_bills,
            type = ServiceType.BILL_PAYMENT
        ),
        ServiceShortcut(
            id = "shortcut_order_food",
            title = "Order Food",
            subtitle = "Top restaurants",
            iconResId = R.drawable.ic_food,
            type = ServiceType.FOOD_DELIVERY
        ),
        ServiceShortcut(
            id = "shortcut_book_ride",
            title = "Book Ride",
            subtitle = "Cabs & bikes",
            iconResId = R.drawable.ic_ride,
            type = ServiceType.RIDE_HAILING
        ),
        ServiceShortcut(
            id = "shortcut_shop_online",
            title = "Shop Online",
            subtitle = "Daily deals",
            iconResId = R.drawable.ic_shop,
            type = ServiceType.SHOPPING
        ),
        ServiceShortcut(
            id = "shortcut_travel",
            title = "Travel",
            subtitle = "Flights & hotels",
            iconResId = R.drawable.ic_travel,
            type = ServiceType.TRAVEL
        )
    )
    
    private val rewards = listOf(
        Reward(
            id = "reward_001",
            title = "Premium Stickers",
            description = "Unlock animated sticker pack",
            pointsRequired = 250,
            iconResId = R.drawable.ic_rewards
        ),
        Reward(
            id = "reward_002",
            title = "Ghost Mode Boost",
            description = "Extended privacy controls",
            pointsRequired = 420,
            iconResId = R.drawable.ic_super_app
        ),
        Reward(
            id = "reward_003",
            title = "Travel Voucher",
            description = "$20 off on flights",
            pointsRequired = 640,
            iconResId = R.drawable.ic_travel
        )
    )
    
    fun getWalletBalance(): Flow<WalletBalance> = walletBalanceFlow.asStateFlow()
    
    fun getTransactions(): Flow<List<WalletTransaction>> = flowOf(transactions)
    
    fun getMiniApps(): Flow<List<MiniApp>> = flowOf(miniApps)
    
    fun getServiceShortcuts(): Flow<List<ServiceShortcut>> = flowOf(serviceShortcuts)
    
    fun getRewards(): Flow<List<Reward>> = flowOf(rewards)
    
    suspend fun addFunds(amount: Double) {
        val current = walletBalanceFlow.value
        walletBalanceFlow.emit(current.copy(totalBalance = current.totalBalance + amount))
    }
    
    suspend fun deductFunds(amount: Double) {
        val current = walletBalanceFlow.value
        walletBalanceFlow.emit(current.copy(totalBalance = (current.totalBalance - amount).coerceAtLeast(0.0)))
    }
    
    suspend fun addLoyaltyPoints(points: Int) {
        val current = walletBalanceFlow.value
        walletBalanceFlow.emit(current.copy(loyaltyPoints = current.loyaltyPoints + points))
    }
}
