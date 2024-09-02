package com.example.financeapplication.app.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.financeapplication.R
import com.example.financeapplication.app.viewmodel.FinancialViewModel
import com.example.financeapplication.data.jsons.StockDetailJson
import com.example.financeapplication.databinding.FragmentStockItemDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StockItemDetailFragment : Fragment() {


    private val mainViewModel by viewModels<FinancialViewModel>()
    private var binding: FragmentStockItemDetailBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = arguments?.getString("stockItemSymbol")
        if (uri != null) {
            mainViewModel.getStockDetails(uri)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStockItemDetailBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.apply {
            stockDetailLD.observe(viewLifecycleOwner) {
                updateUI(it)
            }
        }

    }

    private fun updateUI(stockDetailJson: StockDetailJson?) {

        val stockText = "Stock : ${stockDetailJson?.quoteType?.longName}"

        val stockMarketStateText =
            "State : ${stockDetailJson?.price?.marketState}"
        val openChangeText =
            "Open  : ${stockDetailJson?.price?.regularMarketOpen?.fmt}  ${stockDetailJson?.price?.currencySymbol}"
        val previousCloseText =
            "Price previous close :  ${stockDetailJson?.price?.regularMarketPreviousClose?.fmt} ${stockDetailJson?.price?.currencySymbol}"
        val regularMarketChangePercentText =
            " ${stockDetailJson?.price?.regularMarketChangePercent?.raw} ${stockDetailJson?.price?.regularMarketChangePercent?.fmt}"
        binding?.apply {
            if (stockDetailJson?.price?.marketState == "CLOSED") {
                image.setImageResource(R.drawable.baseline_close_24)
            }else{
                image.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            }

            title.text = stockText
            marketstate.text = stockMarketStateText
            openchange.text = openChangeText
            previousclose.text = previousCloseText

            changepourcent.text = regularMarketChangePercentText

            if ((stockDetailJson?.price?.regularMarketChangePercent?.raw ?: 0.0) > 0.0) {
                changepourcent.setTextColor(Color.parseColor("#00ff00"))
                imagechange.setImageResource(R.drawable.baseline_arrow_drop_up_24)
                imagechange.setColorFilter(Color.parseColor("#00ff00"))
            } else {

                changepourcent.setTextColor(Color.parseColor("#Ff0000"))
                imagechange.setImageResource(R.drawable.baseline_arrow_drop_down_24)
                imagechange.setColorFilter(Color.parseColor("#Ff0000"))
            }
        }
    }

}