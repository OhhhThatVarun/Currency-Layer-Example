package com.varun.currencylayer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.varun.currencylayer.R
import com.varun.currencylayer.data.models.Currency
import com.varun.currencylayer.databinding.ItemSpinnerCurrencyBinding
import com.varun.currencylayer.extentions.getFlagResource

class SpinnerAdapter(context: Context) :
    ArrayAdapter<String>(context, R.layout.item_spinner_currency, arrayListOf()) {

    private var currencies: List<Currency> = ArrayList()

    fun setDataList(currencies: List<Currency>) {
        this.currencies = currencies
        notifyDataSetChanged()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getCount(): Int {
        return currencies.size
    }

    private fun getCustomView(position: Int, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val binding = ItemSpinnerCurrencyBinding.inflate(inflater, parent, false)
        val currency = Currency()
        currency.code = currencies[position].code
        currency.name = currencies[position].name
        currency.countryFlag = context.resources.getFlagResource(currency.code)
        binding.currency = currency
        binding.executePendingBindings()
        return binding.root
    }
}