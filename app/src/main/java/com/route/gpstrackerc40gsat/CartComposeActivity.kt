package com.route.gpstrackerc40gsat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.gpstrackerc40gsat.ui.theme.GPSTrackerC40GSatTheme

class CartComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Data Domain Presentation
        setContent {
            GPSTrackerC40GSatTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    innerPadding
                    CartItemCounter(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun CartItemCounter(modifier: Modifier = Modifier) {
    // Recomposition
    // States
    // remember => Survive recompositions
    var counter by remember {
        mutableIntStateOf(0)
    }

    // Restartable ->
    // Stable  ->

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        UpdateCounterCartButton("-", onButtonClicked = {
            Log.e("TAG", "CartItemCounter: Clicked on Minus")
            if (counter > 0)
                counter--
            Log.e("TAG", "CartItemCounter: counter $counter")
        })
        Text(
            text = "$counter",
            modifier = Modifier
                .padding(16.dp),
            fontSize = 20.sp
        ) // String Interpolation
        UpdateCounterCartButton("+", onButtonClicked = {
            Log.e("TAG", "CartItemCounter: Clicked on Plus")
            counter++
            Log.e("TAG", "CartItemCounter: counter $counter")
        })
    }
}

// 1- interface callback
// 2- Variable              // Type  -
fun add(num1: Int, num2: Int): Int {
    val result = num1 + num2
    return result
}

var addCalculatorOperation: (num1: Int, num2: Int) -> Int = { x, y ->
    val result = x + y
    result
}


@Composable
fun UpdateCounterCartButton(
    text: String,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(modifier = modifier, onClick = {
        onButtonClicked()
    }, shape = RoundedCornerShape(8.dp)) {
        Text(text = text, fontSize = 22.sp)
    }
}

@Preview
@Composable
private fun UpdateCounterCartButtonPreview() {
    UpdateCounterCartButton("+", onButtonClicked = {})
}

@Preview(showSystemUi = true)
@Composable
private fun CartItemCounterPreview() {
    CartItemCounter()
}