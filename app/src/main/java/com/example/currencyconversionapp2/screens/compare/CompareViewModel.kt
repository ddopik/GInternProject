package com.example.currencyconversionapp2.screens.compare

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconversionapp2.api.data.APIRemoteData
import com.example.currencyconversionapp2.api.data.network.APIClient
import com.example.currencyconversionapp2.api.model.ComparisonObject
import com.example.currencyconversionapp2.screens.convert.model.ConversionRatesResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompareViewModel : ViewModel() {


    var currentSelectedBaseCurrency: String = ""
    var firstTargetCurrencyCode: String = ""
    var secondTargetCurrencyCode: String = ""

    val firstTargetCurrencyRate = mutableStateOf("")
    val secondTargetCurrencyRate = mutableStateOf("")

    fun compareCurrency(amount: Int) {
        if (currentSelectedBaseCurrency.isNotBlank() && firstTargetCurrencyCode.isNotEmpty() && secondTargetCurrencyCode.isNotEmpty()) {
            viewModelScope.launch {
                val apiService = APIClient.getClient()?.create(APIRemoteData::class.java)
                try {
                    val comparisonObject =
                        ComparisonObject(base_code = currentSelectedBaseCurrency, mutableListOf(firstTargetCurrencyCode, secondTargetCurrencyCode))
                    val call: Call<ConversionRatesResponse>? = apiService?.compareRates(comparisonObject)

                    call?.enqueue(object : Callback<ConversionRatesResponse> {
                        override fun onResponse(call: Call<ConversionRatesResponse>, response: Response<ConversionRatesResponse>) {
                            val response = response.body()?.conversion_rates
                            firstTargetCurrencyRate.value = (response?.get(firstTargetCurrencyCode)?.times(amount.toDouble())).toString()
                            secondTargetCurrencyRate.value = response?.get(secondTargetCurrencyCode)?.times(amount.toDouble()).toString()
                        }
                        override fun onFailure(call: Call<ConversionRatesResponse>, t: Throwable) {
                            Log.e("ConvertViewModel", "convert Error" + t.message)
                        }
                    })
                } catch (e: Exception) {
//                errorMessage = e.message.toString()
                    Log.e("ConvertViewModel", "convert Error" + e.message)
                }
            }
        }
    }
}