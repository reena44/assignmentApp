package com.example.assignmenttestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmenttestapp.model.AppData
import com.example.assignmenttestapp.model.Images
import com.example.assignmenttestapp.ui.theme.AssignmentTestAppTheme
import com.example.assignmenttestapp.viewModel.AssignmentViewModel

/**
 *<h1>MainActivity</h1>

 *<p>MainActivity class is used to perform the main compose functionality</p>
 *@author Reena<bainsreena44@gmail.com>
 *@since 6/09/23
 **/

class MainActivity : ComponentActivity() {

    val appData = AppData()
    private val viewModel: AssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** initialize data*/
        setupData(appData)

        setContent {
            AssignmentTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetComposeLayout(this,viewModel)
                }
            }
        }
    }

    /** setupData */
    private fun setupData(appData: AppData) {
        val arrayList = ArrayList<Images>()
        arrayList.add(Images(R.drawable.airtel,"UGX, 10,000"))
        arrayList.add(Images(R.drawable.flexi,"UGX, 12,000"))
        arrayList.add(Images(R.drawable.mtn,"UGX, 16,000"))
        appData.validDate = "29-May-2024"
        appData.amount = "UGX 10,000"
        appData.frequency = "As presented"
        appData.blockMessage = "The amount will be blocked when ride is booked with safeBoda,subject to above mentioned limits and validity.The Limit is upto UGX, 10,000"
        appData.imageList = arrayList
        viewModel.setData(appData)


    }

}

/** GetComposeLayout */
@Composable
fun GetComposeLayout(context: MainActivity, viewModel: AssignmentViewModel) {
    val scrollState = rememberScrollState()
    val cardIndex = remember {
        mutableStateOf(0)
    }

    var appData = AppData()
    viewModel.data.observe(context){
        appData = it
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(Color.White)) {
        TopBar(
            title = stringResource(id = R.string.mandate_details),
            painter = painterResource(R.drawable.ic_baseline_arrow_back_ios_24),
            onButtonClicked = { context.finish() })
        Spacer(modifier = Modifier.height(20.dp))
        DetailLayout(appData)
        Spacer(modifier = Modifier.height(20.dp))
        PaymentOptionsLayout(appData,cardIndex)
        Spacer(modifier = Modifier.height(20.dp))
        PayUsingLayout(appData,cardIndex)

    }
}

/** PayUsingLayout */
@Composable
fun PayUsingLayout(
    appData: AppData,
    cardIndex: MutableState<Int>,
) {

    Card(
        modifier = Modifier
            .padding(10.dp, end = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    "Pay Using",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

            }
            Card(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.darkOrange)),
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column (
                        Modifier
                            .weight(1.5f)
                            .padding(start = 4.dp)){
                        Image(
                            painter = painterResource(id =  R.drawable.airtel1),
                            contentDescription = "avatar",
                            modifier = Modifier
                                .width(30.dp)
                                .height(20.dp)
                        )

                    }
                    Column (Modifier.weight(8f)){

                        appData.imageList?.get(cardIndex.value)?.let {
                            Text(
                                it.paymentInfo,
                                Modifier.padding(top = 10.dp, end = 10.dp, bottom = 10.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Column (Modifier.weight(1.5f)){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                            "",Modifier.padding(10.dp),tint = Color.Black
                        )

                    }

                }

            }
        }
    }


}

/** PaymentOptionsLayout */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PaymentOptionsLayout(
    appData: AppData,
    cardIndex: MutableState<Int>,
) {
    Card(
        modifier = Modifier
            .padding(10.dp, end = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Autopay Payment Options",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,)

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 4.dp)
                        .width(100.dp)
                        .height(120.dp)
                        .weight(1f), shape = RoundedCornerShape(10.dp),
                    backgroundColor =if(cardIndex.value == 0) colorResource(id = R.color.lightCream) else colorResource(id = R.color.white),
                    border = BorderStroke(2.dp, if(cardIndex.value == 0) colorResource(id = R.color.darkOrange) else colorResource(id = R.color.white)),
                    elevation = 10.dp,
                    onClick = {
                        cardIndex.value = 0

                    },
                ) {
                    appData.imageList?.get(0)?.imageId?.let { ImageLayout(it) }
                }

                Card(
                    modifier = Modifier
                        .padding(top = 6.dp, end = 4.dp)
                        .width(100.dp)
                        .height(120.dp)
                        .weight(1f), shape = RoundedCornerShape(10.dp),
                    backgroundColor =if(cardIndex.value == 1) colorResource(id = R.color.lightCream) else colorResource(id = R.color.white),
                    border = BorderStroke(2.dp, if(cardIndex.value == 1) colorResource(id = R.color.darkOrange) else colorResource(id = R.color.white)),
                    elevation = 10.dp,
                    onClick = {
                        cardIndex.value = 1

                    },
                ) {
                    appData.imageList?.get(1)?.imageId?.let { ImageLayout(it) }
                }

                Card(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .width(100.dp)
                        .height(120.dp)
                        .weight(1f), shape = RoundedCornerShape(10.dp),
                    backgroundColor =if(cardIndex.value == 2) colorResource(id = R.color.lightCream) else colorResource(id = R.color.white),
                    border = BorderStroke(2.dp, if(cardIndex.value == 2) colorResource(id = R.color.darkOrange) else colorResource(id = R.color.white)),
                    elevation = 10.dp,
                    onClick = {
                        cardIndex.value = 2

                    },
                ) {
                    appData.imageList?.get(2)?.imageId?.let { ImageLayout(it) }
                }

            }

        }
    }
}

/** ImageLayout */
@Composable
fun ImageLayout(imageId: Int) {
    Image(
        painter = painterResource(id =  imageId),
        contentDescription = "avatar",
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

/** DetailLayout */
@Composable
fun DetailLayout( appData: AppData) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(Modifier.padding(start = 10.dp, top = 10.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(Modifier.weight(1f)) {
                    Row{
                        Text("Valid till- ", color = Color.Gray, fontSize = 12.sp)
                        Text(appData.validDate, color = Color.Black, fontSize = 12.sp)


                    }
                }

                Column(Modifier.weight(1f)) {
                    Row{
                        Text("Upto Amount-", color = Color.Gray,fontSize = 12.sp)
                        Text(appData.amount, color = Color.Black,fontSize = 12.sp)

                    }

                }

            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Divider(color = Color.Gray, thickness = 1.dp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column() {
                    Row {
                        Text("Frequency - ", color = Color.Gray,fontSize = 12.sp)
                        Text(appData.frequency, color = Color.Black,fontSize = 12.sp)


                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Divider(color = Color.Gray, thickness = 1.dp)

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Card(
                        modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 10.dp,
                        backgroundColor = colorResource(id = R.color.lightCream)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
                                    "",Modifier.padding(10.dp),tint = Color(0xFFEEA81C)
                                )

                            }
                            Column {

                                Text(
                                    appData.blockMessage,
                                    Modifier.padding(top = 10.dp, end = 10.dp, bottom = 10.dp),
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }

                        }

                    }


                }

            }
        }
    }

}

/** TopBar */

@Composable
fun TopBar(title: String = "", painter: Painter, onButtonClicked: () -> Unit, ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,

                )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(
                    painter, contentDescription = "", tint = Color(0xFFEEA81C),
                )
            }
        },
        backgroundColor = Color.White
    )
}