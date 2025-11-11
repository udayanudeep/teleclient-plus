package com.telegramplus.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.telegramplus.app.R
import com.telegramplus.app.data.repository.SuperAppRepository
import com.telegramplus.app.databinding.FragmentSuperAppBinding
import com.telegramplus.app.ui.adapters.MiniAppAdapter
import com.telegramplus.app.ui.adapters.RewardAdapter
import com.telegramplus.app.ui.adapters.ServiceShortcutAdapter
import com.telegramplus.app.ui.adapters.TransactionAdapter
import com.telegramplus.app.ui.viewmodel.SuperAppViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class SuperAppFragment : Fragment() {
    
    private var _binding: FragmentSuperAppBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: SuperAppViewModel
    
    private lateinit var serviceAdapter: ServiceShortcutAdapter
    private lateinit var miniAppAdapter: MiniAppAdapter
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var rewardAdapter: RewardAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperAppBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapters()
        bindRecyclerViews()
        observeViewModel()
        setupActions()
    }
    
    private fun setupViewModel() {
        val repository = SuperAppRepository()
        viewModel = SuperAppViewModel(repository)
    }
    
    private fun setupAdapters() {
        serviceAdapter = ServiceShortcutAdapter { shortcut ->
            // TODO: Navigate to selected service
        }
        miniAppAdapter = MiniAppAdapter { miniApp ->
            viewModel.selectMiniApp(miniApp)
            // TODO: Open mini app via deep link
        }
        transactionAdapter = TransactionAdapter()
        rewardAdapter = RewardAdapter { reward ->
            // TODO: Redeem reward
        }
    }
    
    private fun bindRecyclerViews() {
        binding.rvServiceShortcuts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = serviceAdapter
        }
        binding.rvMiniApps.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = miniAppAdapter
        }
        binding.rvTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionAdapter
        }
        binding.rvRewards.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = rewardAdapter
        }
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.walletBalance.collect { balance ->
                        val format = NumberFormat.getCurrencyInstance(Locale.US)
                        format.currency = Currency.getInstance(balance.currency)
                        binding.tvWalletBalance.text = format.format(balance.totalBalance)
                        binding.tvLoyaltyPoints.text = getString(
                            R.string.loyalty_points
                        ).let { label ->
                            "$label: ${balance.loyaltyPoints} · ${balance.tier.name.lowercase().replaceFirstChar { it.uppercase() }}"
                        }
                    }
                }
                launch {
                    viewModel.serviceShortcuts.collect { shortcuts ->
                        serviceAdapter.submitList(shortcuts)
                    }
                }
                launch {
                    viewModel.miniApps.collect { apps ->
                        miniAppAdapter.submitList(apps)
                    }
                }
                launch {
                    viewModel.transactions.collect { tx ->
                        transactionAdapter.submitList(tx)
                    }
                }
                launch {
                    viewModel.rewards.collect { rewards ->
                        rewardAdapter.submitList(rewards)
                    }
                }
            }
        }
    }
    
    private fun setupActions() {
        binding.btnScanPay.setOnClickListener {
            // TODO: Launch QR scanner
        }
        binding.btnSendMoney.setOnClickListener {
            // TODO: Launch send money flow
        }
        binding.tvViewAllMiniApps.setOnClickListener {
            // TODO: Navigate to mini app catalog
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
