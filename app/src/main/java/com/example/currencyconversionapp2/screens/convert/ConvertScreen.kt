package com.example.currencyconversionapp.screens.convert

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconversionapp.api.APIViewModel
import com.example.currencyconversionapp.api.model.ConversionResult
import com.example.currencyconversionapp.screens.compare.DropDownMenu
import com.example.currencyconversionapp.screens.favourite.CustomDialogUI
import com.example.currencyconversionapp2.R
import com.example.currencyconversionapp2.api.data.APIRemoteData
import com.example.currencyconversionapp2.viewModels.ConvertViewModel
import com.example.currencyconversionapp2.viewModels.FavouritesViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertScreen(  favouritesViewModel: FavouritesViewModel, convertViewModel: ConvertViewModel) {

   val conversion = convertViewModel.conversionResultFlow.collectAsState()

    var open by remember {
        mutableStateOf(false
        )
    }
    var amount by remember {
        mutableStateOf(0.0)
    }

    var amountTo by remember {
        mutableStateOf(TextFieldValue())
    }

    var target by remember {
        mutableStateOf("")
    }
    var current by remember {
        mutableStateOf("")
    }



//    val currencies = viewModel.currenciesFlow.collectAsState()
//    val convert = viewModel.conversionResultFlow.collectAsState()

    //val conversionresult = viewModel.conversionResultFlow.collectAsState()


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Amount",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(600)),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.3F)
                    .padding(start = 36.dp)
            )
            Text(
                modifier = Modifier
                    .weight(0.8F)
                    .padding(end = 40.dp),
                text = "From", textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.poppins_regular))

                )
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = amount.toString(),
                onValueChange = {
                    amount = it.toDouble()
                   // viewModel.currentSelectedAmount.value = it

                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF9F9F9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFC5C5C5),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .width(130.dp)
                    .height(48.dp)
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DropDownMenu(onItemClicked = {
                   current = it
                   // viewModel.currentSelectedFromCurrency.value = it
                })
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "To",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(600)),
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.3F)
                    .padding(start = 36.dp)
            )
            Text(
                modifier = Modifier
                    .weight(0.4F)
                    .padding(end = 30.dp),
                text = "Amount", textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.poppins_regular))

                )
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DropDownMenu(onItemClicked = {
                    target = it
                    //viewModel.currentSelectedToCurrency.value = it
                })
            }
            TextField(
                value = convertViewModel.convertResult.value,
                onValueChange = {

                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF9F9F9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFC5C5C5),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .width(130.dp)
                    .height(48.dp)
            )

        }//viewModel.convertResult()
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = { convertViewModel.convert(current, target, amount)},
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF363636))

        ) {
            Text(text = "Convert", fontSize = 16.sp, fontWeight = FontWeight(600), fontFamily = FontFamily(Font(R.font.poppins_regular)))
        }
        Spacer(modifier = Modifier.height(25.dp))
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
                .height(1.5.dp)
                .background(color = Color(0xFFE9E9E9))
        )





        Spacer(modifier = Modifier.height(34.dp))



            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "live exchange rate", fontSize = 16.sp, fontWeight = FontWeight(600))

                Card(

                    modifier = Modifier
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE9E9E9),
                            shape = RoundedCornerShape(size = 18.dp)
                        )

                        .padding(10.dp),


                    ) {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .clickable { open = !open }, verticalAlignment = Alignment.CenterVertically


                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_1),
                            contentDescription = "", modifier = Modifier
                                .padding(end = 10.dp)
                                .size(25.dp)

                        )
                        Text(text = "Add to Favorites", color = Color.Black)

                    }

                }


            }



        Text(
            text = "My Portfolio",
            modifier = Modifier.padding(start = 25.dp, top = 15.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        Column {
            
            
            
            //displayDataFromApiMyPortfolio(viewModel = viewModel)
            

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp)
                    .height(1.dp)
                    .background(color = Color(0xFFE9E9E9))
            )
        }

    }

if(open){
    CustomDialogUI(onClick = {open = false},  favouritesViewModel = favouritesViewModel )
}


}





