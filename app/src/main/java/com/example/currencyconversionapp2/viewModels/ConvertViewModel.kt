package com.example.currencyconversionapp2.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconversionapp.api.model.ConversionResult
import com.example.currencyconversionapp.api.model.Currency
import com.example.currencyconversionapp2.api.data.APIRemoteData
import com.example.currencyconversionapp2.api.data.network.APIClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConvertViewModel: ViewModel() {

    var currentSelectedToCurrency = MutableStateFlow<String>("")
    var currentSelectedAmount = MutableStateFlow<Double>(0.0)
    var convertResult = mutableStateOf<String>("")



    private val mutableConversionResultFlow = MutableStateFlow<Double>(0.0)
    val conversionResultFlow: StateFlow<Double> = mutableConversionResultFlow


    var errorMessage: String by mutableStateOf("")

    fun convert(current: String, target: String, amount: Double){
        viewModelScope.launch {
            val apiService = APIClient.getClient()?.create(APIRemoteData::class.java)
            try {
               // val convertRes = apiService?.getConversionResult(base, target, amount)

                val call: Call<ConversionResult> = apiService!!.getConversionResult(current, target, amount)

                call.enqueue(object : Callback<ConversionResult> {
                    override fun onResponse(call: Call<ConversionResult>, response: Response<ConversionResult>) {
                        val model: ConversionResult? = response.body()
                        val resp =
                            model?.conversion_result?:0
                        convertResult.value = resp.toString()
                    }
                    override fun onFailure(call: Call<ConversionResult>, t: Throwable) {

                        Log.e("ConvertViewModel","convert Error"+t.message)
                    }


            })} catch (e : Exception){
                errorMessage = e.message.toString()
                 Log.e("ConvertViewModel","convert Error"+e.message)
            }


        }

    }



}