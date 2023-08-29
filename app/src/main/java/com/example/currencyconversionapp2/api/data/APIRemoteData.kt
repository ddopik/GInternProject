package com.example.currencyconversionapp2.api.data

import com.example.currencyconversionapp.api.model.ConversionRate
import com.example.currencyconversionapp.api.model.ConversionResult
import com.example.currencyconversionapp.api.model.Currency
import com.example.currencyconversionapp2.api.model.ComparisonObject
import com.example.currencyconversionapp2.screens.convert.model.ConversionRatesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIRemoteData {
    @GET("currencies")
    suspend fun getCurrencies(): List<Currency>

    @GET("currencies/{current}/{target}")
    suspend fun getConversionRate(@Path("current") current: String,
                                  @Path("target") target: String): ConversionRate

    @GET("currencies/{current}/{target}/{amount}")
     fun getConversionResult(@Path("current") current: String,
                                    @Path("target") target: String,
                                    @Path("amount") amount : Double): Call<ConversionResult>

    @POST("currencies/comparison")
    fun compareRates(@Body comparisonObject : ComparisonObject): Call<ConversionRatesResponse>


}