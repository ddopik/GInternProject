package com.example.currencyconversionapp.screens.compare

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconversionapp2.R
import com.example.currencyconversionapp2.screens.compare.CompareViewModel


@Composable
fun CompareLayout(compareViewModel: CompareViewModel) {
//TopImageWithText()
    CompareBox(compareViewModel)
    //LastBox()
    // MyDialogUIPreview()

}


/**COMPARE BOX FUNCTION CONTAINS AMOUNT AND FROM TEXT, TEXT FIELD FOR AMOUNT, DROPDOWN MENU
AND TARGET CURRENCY TEXT
 * */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CompareBox(compareViewModel: CompareViewModel) {

    var amountFrom by remember {
        mutableStateOf(1)
    }
    var amountTo by remember {
        mutableStateOf(1)
    }



    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Amount",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.3F)
                    .padding(start = 36.dp)
            )
            //  Spacer(modifier = Modifier.width(1.dp))

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
                value = amountFrom.toString(),
                onValueChange = {
                    amountFrom = it.toIntOrNull() ?: 1
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF9F9F9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), modifier = Modifier
                    .border(width = 0.5.dp, color = Color(0xFFC5C5C5), shape = RoundedCornerShape(size = 20.dp))
                    .padding(0.5.dp)
                    .width(160.dp)
                    .height(48.dp)
                    .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp))

            )

            Row(
                modifier = Modifier
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                DropDownMenu(onItemClicked = {
                    compareViewModel.currentSelectedBaseCurrency = it
                })
            }

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Targeted Currency",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(600)),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                modifier = Modifier
                    .weight(0.3F)
                    .padding(start = 20.dp)
            )
            Text(
                modifier = Modifier
                    .weight(0.4F)
                    .padding(end = 30.dp),
                text = "Targeted Currency", textAlign = TextAlign.Center,
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
                    compareViewModel.firstTargetCurrencyCode = it
                })
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DropDownMenu(onItemClicked = {
                    compareViewModel.secondTargetCurrencyCode = it
                })
            }
        }


        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp)),
                // .width(180.dp),
                ///.background(color = Color(0xFFF9F9F9)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    value = compareViewModel.firstTargetCurrencyRate.value,
                    onValueChange = {
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF9F9F9),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ), modifier = Modifier
                        .border(width = 0.5.dp, color = Color(0xFFC5C5C5), shape = RoundedCornerShape(size = 20.dp))
                        .padding(0.5.dp)
                        .width(160.dp)
                        .height(48.dp)
                        .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp))

                )
            }
            TextField(
                value = compareViewModel.secondTargetCurrencyRate.value,
                onValueChange = {

                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFF9F9F9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), modifier = Modifier
                    .border(width = 0.5.dp, color = Color(0xFFC5C5C5), shape = RoundedCornerShape(size = 20.dp))
                    .padding(0.5.dp)
                    .width(160.dp)
                    .height(48.dp)
                    .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 20.dp))

            )


        }
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = { compareViewModel.compareCurrency(5) },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF363636))

        ) {
            Text(text = "Compare",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
                .height(1.5.dp)
                .background(color = Color(0xFFE9E9E9)))

    }


}






//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LastBox(){
//
//
//   Box(modifier = Modifier
//       .fillMaxWidth()
//       .height(100.dp)
//   ) {
//        Column (modifier = Modifier
//            .fillMaxSize()
//            .width(100.dp)){
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 500.dp)
//            ) {
//                TextField(
//                    value = "hello", onValueChange = {},
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color(0xFFF9F9F9),
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent
//                    ),
//                    modifier = Modifier
//                        .border(
//                            width = 1.dp, color = Color(0xFFC5C5C5),
//                            shape = RoundedCornerShape(size = 20.dp)
//                        )
//
//                        .padding(1.dp)
//                        .width(130.dp)
//                        .height(60.dp)
//                        .background(
//                            color = Color(0xFFF9F9F9),
//                            shape = RoundedCornerShape(size = 20.dp)
//                        ),
//
//                    )
//
//
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 50.dp)
//                ) {
//
//                    DropDownMenu()
//
//
//                }
//
//
//            }
//        }
//    }
//}
//