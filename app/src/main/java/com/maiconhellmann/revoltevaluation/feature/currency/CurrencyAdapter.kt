package com.maiconhellmann.revoltevaluation.feature.currency

import android.icu.util.Currency
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.domain.entity.Rate
import com.maiconhellmann.revoltevaluation.R
import com.maiconhellmann.revoltevaluation.util.extensions.getString
import com.maiconhellmann.revoltevaluation.util.extensions.inflate
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    var rateList: List<Rate> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_currency)) {

        fun bind(model: Rate) = with(itemView) {
            textViewCurrencyCode.text = model.currency

            textViewCurrencyDisplayName.text = model.currencyDisplayName

            editTextCurrencyValue.setText(getString(R.string.currency_value, model.value))

            itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = rateList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rateList[position])
    }
}