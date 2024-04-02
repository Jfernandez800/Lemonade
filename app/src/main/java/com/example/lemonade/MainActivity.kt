package com.example.lemonade

import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

/*
* 1. When the user first launches the app, they see a lemon tree. There's a label that prompts them
to tap the lemon tree image to "select" a lemon from the tree.
* 2. After they tap the lemon tree, the user sees a lemon. They are prompted to tap the lemon to
"squeeze" it to make lemonade. They need to tap the lemon several times to squeeze it. The number
of taps required to squeeze the lemon is different each time and is a randomly generated number
between 2 to 4 (inclusive).
* 3. After they've tapped the lemon the required number of times, they see a refreshing glass of
lemonade! They are asked to tap the glass to "drink" the lemonade.
* 4. After they tap the lemonade glass, they see an empty glass. They are asked to tap the empty
glass to start again.
* 5. After they tap the empty glass, they see the lemon tree and can begin the process again. More
lemonade please!
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme() {
            }
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }
    //same for the squeezeCnt.
    var squeezeCnt by remember { mutableStateOf(0) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        //checks to see what number currentStep is in to call the step its on.
        when (currentStep) {
            1 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.tree_content_desc),
                        modifier = Modifier
                            .wrapContentSize()
                            .width(150.dp)
                            .height(150.dp)
                            .background(Color.Yellow)
                            .clickable {
                                currentStep = 2 //when clicked change currentStep value to 2.
                                squeezeCnt = (2..4).random() //random number generator for the number of clicks.
                            }
                    )
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .padding(32.dp))
                    Text(text = stringResource(R.string.tree))
                }
            }
            2 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.squeeze_content_desc),
                        modifier = Modifier.wrapContentSize()
                            .width(150.dp)
                            .height(150.dp)
                            .background(Color.Green)
                            .clickable {
                                squeezeCnt-- //subtract squeezeCnt.
                                if (squeezeCnt == 0) { //if 0 change currentStep to 3.
                                    currentStep = 3
                                }
                            }
                    )
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .padding(32.dp))
                    Text(text = stringResource(R.string.squeeze))
                }
            }
            3 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.drink_content_desc),
                        modifier = Modifier
                            .wrapContentSize()
                            .width(150.dp)
                            .height(150.dp)
                            .background(Color.Cyan)
                            .clickable {
                                currentStep = 4
                            }
                    )
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .padding(32.dp))
                    Text(text = stringResource(R.string.drink))
                }
            }
            4 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.restart_content_desc),
                        modifier = Modifier
                            .wrapContentSize()
                            .width(150.dp)
                            .height(150.dp)
                            .background(Color.Red)
                            .clickable {
                                currentStep = 1
                            }
                    )
                    Spacer(modifier = Modifier
                        .height(32.dp)
                        .padding(32.dp))
                    Text(text = stringResource(R.string.restart))
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme() {
        LemonadeApp()
    }
}