package com.example.financeapplication.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapplication.data.jsons.Result
import com.example.financeapplication.data.jsons.StockDetailJson
import com.example.financeapplication.data.jsons.SummaryMarketJson
import com.example.financeapplication.data.repository.FinancialRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialViewModel @Inject constructor(private val repository: FinancialRepository) :
    ViewModel() {

    var stockListLD: MutableLiveData<SummaryMarketJson> = MutableLiveData()

    var stockDetailLD: MutableLiveData<StockDetailJson> = MutableLiveData()

    private val _searchText = MutableStateFlow(" ")

    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)

    val isSearching = _isSearching.asStateFlow()

    private var _stockListSearch =
        MutableStateFlow(stockListLD.value?.marketSummaryAndSparkResponse?.result)


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getMarketStockList() {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getMarketSummary()
            stockListLD.postValue(response.body())
            println("success")
        }
    }

    fun getStockDetails(id: String) {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = repository.getStockDetails(id)
            stockDetailLD.postValue(response.body())
        }

    }

    @OptIn(FlowPreview::class)
    var stockSearchList: StateFlow<List<Result>?> = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_stockListSearch) { text, _ ->
            if (text.isBlank()) {
                stockListLD.value?.marketSummaryAndSparkResponse?.result
            } else {
                stockListLD.value?.marketSummaryAndSparkResponse?.result?.filter {
                    it.fullExchangeName.contains(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            stockListLD.value?.marketSummaryAndSparkResponse?.result
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}