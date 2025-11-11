package com.telegramplus.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telegramplus.app.data.model.LoyaltyTier
import com.telegramplus.app.data.model.MiniApp
import com.telegramplus.app.data.model.Reward
import com.telegramplus.app.data.model.ServiceShortcut
import com.telegramplus.app.data.model.WalletBalance
import com.telegramplus.app.data.model.WalletTransaction
import com.telegramplus.app.data.repository.SuperAppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SuperAppViewModel(
    private val repository: SuperAppRepository
) : ViewModel() {
    
    val walletBalance: StateFlow<WalletBalance> = repository.getWalletBalance()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WalletBalance(0.0, "USD", 0, LoyaltyTier.SILVER)
        )
    
    val transactions: StateFlow<List<WalletTransaction>> = repository.getTransactions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    
    val miniApps: StateFlow<List<MiniApp>> = repository.getMiniApps()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    
    val serviceShortcuts: StateFlow<List<ServiceShortcut>> = repository.getServiceShortcuts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    
    val rewards: StateFlow<List<Reward>> = repository.getRewards()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    
    private val _selectedMiniApp = MutableStateFlow<MiniApp?>(null)
    val selectedMiniApp: StateFlow<MiniApp?> = _selectedMiniApp
    
    val walletOverview: StateFlow<Pair<WalletBalance, List<WalletTransaction>>> = combine(
        walletBalance,
        transactions
    ) { balance, history ->
        balance to history.take(3)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WalletBalance(0.0, "USD", 0, LoyaltyTier.SILVER) to emptyList()
    )
    
    fun selectMiniApp(miniApp: MiniApp) {
        _selectedMiniApp.value = miniApp
    }
    
    fun clearMiniAppSelection() {
        _selectedMiniApp.value = null
    }
    
    fun addFunds(amount: Double) {
        viewModelScope.launch {
            repository.addFunds(amount)
        }
    }
    
    fun deductFunds(amount: Double) {
        viewModelScope.launch {
            repository.deductFunds(amount)
        }
    }
    
    fun rewardUser(points: Int) {
        viewModelScope.launch {
            repository.addLoyaltyPoints(points)
        }
    }
}
