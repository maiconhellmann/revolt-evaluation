package com.maiconhellmann.revoltevaluation.feature.currency

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.domain.entity.Rate
import com.maiconhellmann.icons.CurrencyIconLoader
import com.maiconhellmann.revoltevaluation.R
import com.maiconhellmann.revoltevaluation.util.extensions.getString
import com.maiconhellmann.revoltevaluation.util.extensions.inflate
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyAdapter(private val currencyIconLoader: CurrencyIconLoader) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    var rateList: MutableList<Rate> = mutableListOf()
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

            currencyIconLoader.load(model.currency, imageViewCountryFlag)

            editTextCurrencyValue.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    //TODO tests
                    val currentIndex = rateList.indexOfFirst { it.currency == model.currency }
                    rateList.removeAt(currentIndex)
                    rateList.add(0, model)

                    notifyItemMoved(currentIndex, 0)
                }
            }

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