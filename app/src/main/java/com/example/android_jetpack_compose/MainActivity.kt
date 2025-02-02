package com.example.android_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_jetpack_compose.ui.theme.AndroidJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {


    //Changing the button color  text when you click on it
    val myBackgroundcolor = remember {
        mutableStateOf(Color.Red)
    }

    var name by remember { mutableStateOf("") }


    val myButtonText = remember{
        mutableStateOf("Do your magic")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Button
        Button(onClick = {
            myBackgroundcolor.value = Color.Black
            myButtonText.value = "Compose is fun"
        },
            colors = ButtonDefaults.buttonColors(myBackgroundcolor.value),
            modifier = Modifier.size(250.dp,50.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = myButtonText.value,
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        //TextField
        TextField(
            value = name,
            onValueChange = { name = it},
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp,end=10.dp),
            label = { Text(text = "Enter your name ") },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Red,
                focusedIndicatorColor = Color.Yellow,
                unfocusedIndicatorColor = Color.Red,
                cursorColor = Color.Red,
                
            )
        )







    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}