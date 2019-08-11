package com.maiconhellmann.revoltevaluation.feature.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.maiconhellmann.revoltevaluation.R
import com.maiconhellmann.revoltevaluation.databinding.ActivityCurrencyBinding
import com.maiconhellmann.revoltevaluation.feature.common.ViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding
    private val viewModel: CurrencyViewModel by viewModel()
    private val adapter: CurrencyAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getString(R.string.currency_title).let { title->
            actionBar?.title = title
            supportActionBar?.title = title
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency)

        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.fetchCurrency()

        viewModel.state.observe(this, Observer { state->
            when (state) {
                is ViewState.Success -> {
                    adapter.rateList = state.data.toMutableList()
                }
            }
        })
    }
}
