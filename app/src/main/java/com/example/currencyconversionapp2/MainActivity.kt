package com.example.currencyconversionapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconversionapp.api.APIViewModel
import com.example.currencyconversionapp.screens.compare.CompareLayout
import com.example.currencyconversionapp.screens.convert.ConvertScreen
import com.example.currencyconversionapp2.screens.compare.CompareViewModel
import com.example.currencyconversionapp2.ui.theme.CurrencyConversionApp2Theme
import com.example.currencyconversionapp2.viewModels.ConvertViewModel
import com.example.currencyconversionapp2.viewModels.FavouritesViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: APIViewModel by viewModels()

    private val favViewModel: FavouritesViewModel by viewModels()

    private val convertViewModel: ConvertViewModel by viewModels()
    private val compareViewModel: CompareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConversionApp2Theme {
                var convertScreen by remember {
                    mutableStateOf(true)
                }



                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//
//                    val currency = mainViewModel.currenciesFlow.collectAsState()
//                    LazyColumn{
//                        items(currency.value?.image? ?: listOf<>())
//
//                    }

                    Column(modifier = Modifier) {


                        MainScreen(onConvertClick = { convertScreen = true },
                            onCompareClick = { convertScreen = false })
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)
                        ) {
                            if (convertScreen) {
                                ConvertScreen(favouritesViewModel = favViewModel, convertViewModel = convertViewModel )

                            } else {
                                CompareLayout(compareViewModel = compareViewModel)
                            }
                        }
                    }
                    favViewModel.getAddToFavouritesList()
                  //  mainViewModel.getCurrency(this)

                //    addApiCurrenciesInlist(favouriteModel = mainViewModel)
//                   mainViewModel.remoteCurrencies.observe(this){remoteCurrencies ->
//                       Log.d("MainActivity", "Cached Curencies: $this")
//
//                   }



                }

            }
        }
    }}




//@Composable
//fun addApiCurrenciesInlist(favouriteModel : APIViewModel ){
//
////    LaunchedEffect(Unit) {
////        viewModel.getCarsFromAPI()
////    }
//
//    favouriteModel.getCurrency(LocalContext.current)
//
//    val currLiveData = favouriteModel.currenciesFlow.collectAsState()
//
//    LazyColumn{
////  items(currLiveData.value.z){}
//
//    }
//
//}
//






@Composable
fun MainScreen(
    onConvertClick: () -> Unit,
    onCompareClick: () -> Unit
) {
    var textColorConvertState by remember {
        mutableStateOf(Color.White)
    }
    var textColorCompareState by remember {
        mutableStateOf(Color(0xFFF8F8F8))
    }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(265.dp)
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.top_image), modifier = Modifier

                .fillMaxWidth()
                .size(250.dp), contentDescription = "", contentScale = ContentScale.FillBounds
        )
        Column {
            Image(
                painter = painterResource(id = R.drawable.concurrency_text),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp)
                    .width(143.dp)
                    .height(32.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Currency Converter",
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))

                )
                Text(
                    text = "check live foreign exchange rates",
                    color = Color.White,
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            }
        }
        Card(
            shape = RoundedCornerShape(22.dp), modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Convert",
                    Modifier

                        .background(

                            color = textColorConvertState, shape =
                            RoundedCornerShape(22.dp)
                        )
                        .padding(start = 20.dp)
                        .width(100.dp)

                        .padding(
                            10.dp
                        )
                        .clickable {
                            textColorConvertState = Color.White
                            textColorCompareState = Color(0xFFF8F8F8)

                            onConvertClick(

                            )
                            //visibility = true
                        },

                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(400)),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )



                Text(

                    text = "Compare",
                    Modifier
                        .background(
                            color = textColorCompareState, shape = RoundedCornerShape(22.dp)
                        )

                        .padding(
                            start = 20.dp
                        )
                        .width(100.dp)
                        .padding(10.dp)
                        .clickable {
                            textColorCompareState = Color.White
                            textColorConvertState = Color(0xFFF8F8F8)


                            onCompareClick()


                        },
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(400)),
                    fontFamily = FontFamily(Font(R.font.poppins_regular))

                )
            }
        }
    }
}
