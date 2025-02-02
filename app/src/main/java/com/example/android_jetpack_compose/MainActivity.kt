package com.example.android_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.res.painterResource
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

    val myButtonText = remember {
        mutableStateOf("Do your magic")
    }

    val buttonStatus = remember {
        mutableStateOf(true)
    }

    val myImage = remember {
        mutableStateOf(R.drawable.first)
    }

    //Dynamic text with checkbox
    var myText = remember {
        mutableStateOf("What is your gender?")
    }

    var firstCheckbox = remember {
        mutableStateOf(false)
    }

    var secondCheckbox = remember {
        mutableStateOf(false)
    }

    //Changing background color using radio button
    var myBackground = remember {
        mutableStateOf(Color.White)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myBackground.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(myImage.value),
            contentDescription = "myimage",
            modifier = Modifier.size(300.dp)
        )


        //Button
        Button(
            onClick = {
                myBackgroundcolor.value = Color.Black
                myButtonText.value = "Compose is fun"

                //image
                if (buttonStatus.value) {
                    myImage.value = R.drawable.second
                } else {
                    myImage.value = R.drawable.first
                }
                //End of image
            },
            colors = ButtonDefaults.buttonColors(myBackgroundcolor.value),
            modifier = Modifier.size(250.dp, 50.dp),
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
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = { Text(text = "Enter your name ") },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Red,
                focusedIndicatorColor = Color.Yellow,
                unfocusedIndicatorColor = Color.Red,
                cursorColor = Color.Red,

                )
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Dynamic Text and checkbox Component

        Text(
            text = myText.value,
            fontSize = 20.sp,
            modifier = Modifier
                .width(300.dp)
                .background(Color.Red)
                .padding(20.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Column {

            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked=firstCheckbox.value,
                    onCheckedChange = {
                        firstCheckbox.value=it
                        if (firstCheckbox.value) {
                            myText.value = "Your gender is male"
                        }
                        else {
                            myText.value = "What is your gender?"
                        }

                        },
                    colors = CheckboxDefaults.colors(Color.Gray)
                )
                Text(
                    text = "Male", fontSize = 20.sp
                )
            }


            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked=secondCheckbox.value,
                    onCheckedChange = {
                        secondCheckbox.value=it
                        if (secondCheckbox.value) {
                            myText.value = "Your gender is female"
                        }
                        else {
                            myText.value = "What is your gender?"
                        }
                                      },
                    colors = CheckboxDefaults.colors(Color.Gray)

                )
                Text(
                    text = "Female", fontSize = 20.sp
                )
            }
        }


        //RadioButton


        var radioTexts = listOf("Red","Green","Yellow")
        var colorLIst = arrayListOf(Color.Red, Color.Green, Color.Yellow)
        var radioIndex = remember { mutableStateOf(0) }

        Text(
            text = "Change screen background color....",
            fontSize = 20.sp
        )

        Column {
            radioTexts.forEachIndexed { position, name ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        radioIndex.value = position
                    }){
                    RadioButton(
                        selected =
                        name == radioTexts[radioIndex.value],
                        onClick = {
                            radioIndex.value = position
                            myBackground.value = colorLIst[radioIndex.value]
                        },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Text(text = name, fontSize = 20.sp)
                }

            }
        }

        Button(onClick = { myBackground.value = colorLIst[radioIndex.value] },
            colors = ButtonDefaults.buttonColors(Color.Gray),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Change background color")
        }




    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}