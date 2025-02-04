package com.example.android_jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.android_jetpack_compose.ui.theme.AndroidJetpackComposeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            mydemo()
        }
    }
}

@Composable
fun mydemo() {

    //variables
    val switchState = remember { mutableStateOf(false) }
    val mytext = remember { mutableStateOf("This image is visible") }
    val myBackground = remember { mutableStateOf(Color.White) }

    Column(modifier = Modifier.fillMaxSize().background(myBackground.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        //Switch
        Switch(
            checked = switchState.value,
            onCheckedChange = { switchState.value = it},
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Red,
                uncheckedTrackColor = Color.Gray,
                checkedThumbColor = Color.Yellow,
                uncheckedThumbColor = Color.Black
            )

        )

        if(!switchState.value){
            //Image
            Image(
                painter = painterResource(R.drawable.first),
                contentDescription = "first",
                modifier = Modifier.size(300.dp)
            )
            mytext.value = "This image is visible"
        }
        else{
            Spacer(modifier = Modifier.size(300.dp))
            mytext.value = "This image is invisible"
            myBackground.value = Color.Black

        }




        //Text
        Text(
            text = mytext.value,
            fontSize = 20.sp,
            modifier = Modifier.background(Color.Red).padding(20.dp).width(300.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.size(20.dp))

        //Dropdown Menu
        // Start of Text Field with a dropdown
        var mExpanded by remember { mutableStateOf(false) }
        val options = listOf("250g", "500g", "1 kg")
        var productQuantity by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
        val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

        Column(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Choose product quantity")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                options.forEach {

                        label -> DropdownMenuItem(
                    text = { Text(text = label)},
                    onClick = {
                        productQuantity = label
                        mExpanded = false
                    })


                }
            }
        }
        //End of TextField with dropdown



        //Toast Message
        var context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context,"Welcome to Homepage",Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(Color.Red),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Show Toast Message")
        }





    }

}

@Preview(showBackground = true)
@Composable
fun mydemoPreview() {
    mydemo()
}