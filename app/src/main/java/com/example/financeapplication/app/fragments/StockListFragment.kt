package com.example.financeapplication.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.financeapplication.R
import com.example.financeapplication.app.viewmodel.FinancialViewModel
import com.example.financeapplication.data.jsons.Result
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class StockListFragment : Fragment() {

    private val mainViewModel by viewModels<FinancialViewModel>()

    private lateinit var executor: ScheduledExecutorService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLiveData()
    }

    private fun initLiveData() {
        mainViewModel.apply {
            stockListLD.observe(viewLifecycleOwner) {
                mainViewModel.onSearchTextChange("")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        executor = Executors.newScheduledThreadPool(1)
        executor.scheduleWithFixedDelay({
            mainViewModel.getMarketStockList()
        }, 0, 8, TimeUnit.SECONDS)

    }

    override fun onPause() {
        super.onPause()
        executor.shutdown()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val searchText by mainViewModel.searchText.collectAsState()
                val persons by mainViewModel.stockSearchList.collectAsState()
                val isSearching by mainViewModel.isSearching.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = mainViewModel::onSearchTextChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Search") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isSearching) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                //.fillMaxWidth()
                                .weight(1f)
                        ) {

                            items(persons.orEmpty()) { person ->

                                StockItem(stockItem = person, onItemClick = {

                                    val args = Bundle()
                                    args.putString("stockItemSymbol", it.symbol)
                                    findNavController().navigate(
                                        R.id.action_mobile_navigation_to_stockDetailFragment,
                                        args
                                    )

                                })

                            }
                        }
                    }
                }

            }
        }

    }

    @Composable
    fun StockItem(stockItem: Result, onItemClick: (Result) -> Unit) {
        // Your item UI code here
        // Detect click and invoke the onItemClick lambda

        Card(
            modifier = Modifier.padding(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7F2F9),
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onItemClick(stockItem) }
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = stockItem.fullExchangeName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Previous close : ${stockItem.regularMarketPreviousClose.fmt}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Regular Market time : ${stockItem.regularMarketTime.fmt}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Market :${stockItem.market} - Market state : ${stockItem.marketState} ",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )


                }

            }
        }
    }


}