package com.example.android_jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_jetpack_compose.ui.theme.AndroidJetpackComposeTheme
import kotlinx.coroutines.launch

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            About()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun About() {


    //Snackbar similar to toast message but provides brief message about app
    //at the bottom of the screen.

    val mysnackbarHostState = remember { SnackbarHostState() }
    val myCouritineScope = rememberCoroutineScope()
    val mContext = LocalContext.current


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = mysnackbarHostState
            ){
                Snackbar(
                    snackbarData = it,
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    actionColor = Color.Black,
                    dismissActionContentColor = Color.Blue
                )
            }
        },
        content = {
            Column(modifier = Modifier.fillMaxSize().padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){

                Button(
                    onClick = {
                        myCouritineScope.launch {
                           val result =  mysnackbarHostState.showSnackbar(
                                message = "This is the snackbar message",
                                actionLabel = "Show toast",
                                duration = SnackbarDuration.Indefinite,
                                withDismissAction = true
                            )

                            if (result == SnackbarResult.ActionPerformed){
                                Toast.makeText(mContext,"Action Performed",Toast.LENGTH_LONG).show()
                            }


                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Show Snackbar Message")
                }


                //Dialog Message
                val dialogStatus = remember { mutableStateOf(false) }
                val textColor = remember { mutableStateOf(Color.Black) }
                val context = LocalContext.current

                Button(
                    onClick = {
                        dialogStatus.value = true
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Show Dialog Message", color = textColor.value)

                }



                if (dialogStatus.value){

                    AlertDialog(
                        onDismissRequest = { dialogStatus.value=true },
                        confirmButton = {
                            Button(
                                onClick = {
                                    dialogStatus.value = false
                                    textColor.value = Color.Red
                                    Toast.makeText(context,"Confirm button is clicked",Toast.LENGTH_LONG).show()
                                },
                                colors = ButtonDefaults.buttonColors(Color.Green),
                                shape = RoundedCornerShape(10.dp)

                            ){
                                Text(text = "Yes", color = Color.White, fontSize = 18.sp)
                            }

                                        },
                        dismissButton = {

                            Button(
                                onClick = {
                                    dialogStatus.value = false
                                    Toast.makeText(context,"Dismiss button is clicked",Toast.LENGTH_LONG).show()
                                },
                                colors = ButtonDefaults.buttonColors(Color.Red),
                                shape = RoundedCornerShape(10.dp)

                            ){
                                Text(text = "No", color = Color.White, fontSize = 18.sp) }

                        },
                        title = { Text(text = "Dialog Message", color = Color.White) },
                        text = { Text(text = "Do you want to change the text color of the button?", color = Color.White, fontSize = 20.sp) },
                        containerColor = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )


                }















            }
        }
        )

}


@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    About()
}