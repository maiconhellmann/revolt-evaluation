package com.maiconhellmann.revoltevaluation.feature.currency

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maiconhellmann.domain.entity.Rate
import com.maiconhellmann.icons.CurrencyIconLoader
import com.maiconhellmann.revoltevaluation.R
import com.maiconhellmann.revoltevaluation.util.extensions.getString
import com.maiconhellmann.revoltevaluation.util.extensions.inflate
import kotlinx.android.synthetic.main.item_currency.view.editTextCurrencyValue
import kotlinx.android.synthetic.main.item_currency.view.imageViewCountryFlag
import kotlinx.android.synthetic.main.item_currency.view.textViewCurrencyCode
import kotlinx.android.synthetic.main.item_currency.view.textViewCurrencyDisplayName

class CurrencyAdapter(private val currencyIconLoader: CurrencyIconLoader) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private val textWatcher = createTextWatcherListener()

    var rateList: MutableList<Rate> = mutableListOf()
        set(value) {
            val wasEmpty = field.isEmpty()
            field = value

            //only refreshes everything first time
            if(wasEmpty) {
                notifyDataSetChanged()
            } else {
                rateList.forEachIndexed { index, _ ->
                    if (index > 0) {
                        notifyItemChanged(index)
                    }
                }
            }
        }

    var onBaseCurrencyChanged: ((String, Double) -> Unit)? = null
    var onBaseCurrencyValueChanged: ((String, Double) -> Unit)? = null

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_currency)) {

        fun bind(model: Rate) = with(itemView) {

            editTextCurrencyValue.removeTextChangedListener(textWatcher)
            editTextCurrencyValue.setText(getString(R.string.currency_value, model.value))
            textViewCurrencyCode.text = model.currency
            textViewCurrencyDisplayName.text = model.currencyDisplayName

            currencyIconLoader.load(model.currency, imageViewCountryFlag)

            editTextCurrencyValue.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    val currentIndex = rateList.indexOfFirst { it.currency == model.currency }

                    if (currentIndex > 0) {
                        rateList.removeAt(currentIndex)
                        rateList.add(0, model)

                        notifyItemMoved(currentIndex, 0)

                        onBaseCurrencyChanged?.invoke(model.currency, model.value)

                        editTextCurrencyValue.addTextChangedListener(textWatcher)
                    }
                }
            }

            if (rateList.first().currency == model.currency) {
                editTextCurrencyValue.addTextChangedListener(textWatcher)
            }

            itemView
        }
    }

    private fun createTextWatcherListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Parse input to double
                val newValue = if (s.isNullOrEmpty()) {
                    0.0
                } else {
                    s.toString().toDouble()
                }

                if (rateList.first().currency == rateList.first().base) {
                    onBaseCurrencyValueChanged?.invoke(rateList.first().base, newValue)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = rateList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rateList[position])
    }
}