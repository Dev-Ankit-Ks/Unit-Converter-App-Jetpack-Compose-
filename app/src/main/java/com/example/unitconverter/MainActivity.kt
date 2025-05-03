package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputUnity by remember {
        mutableStateOf("Meters")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var conversionFactor = remember {
        mutableStateOf(1.00)
    }
    var oconversionFactor = remember {
        mutableStateOf(1.00)
    }


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value  * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

   Column (
       modifier = Modifier.fillMaxSize(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ){
       Text(text = "Unit Converter",)
       Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = inputValue, onValueChange = { changedValue->
                inputValue = changedValue
                convertUnits()
            }
        )
       Spacer(modifier = Modifier.height(15.dp))
       Row {
           Box {
               Button(onClick = {
                   iExpanded = true
               }) {
                   Text(text = inputUnit)
                   Icon(Icons.Default.ArrowDropDown , contentDescription = null)
               }
               DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                   DropdownMenuItem(text = { Text(text = "CentiMeter")},
                       onClick = {
                            iExpanded = false
                           inputUnit = "CentiMeter"
                           conversionFactor.value = 0.01
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "Meters")},
                       onClick = {
                           iExpanded = false
                           inputUnit = "Meters"
                           conversionFactor.value = 1.0
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "Feet")},
                       onClick = {
                           iExpanded = false
                           inputUnit = "Feet"
                           conversionFactor.value = 0.3048
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "MilliMeter")},
                       onClick = {
                           iExpanded = false
                           inputUnit = "MilliMeter"
                           conversionFactor.value = 0.001
                           convertUnits()
                       })
               }
           }
           Spacer(modifier = Modifier.width(19.dp))
           Box {
               Button(onClick = {
                   oExpanded = true
               }) {
                   Text(text = outputUnity)
                   Icon(Icons.Default.ArrowDropDown , contentDescription = null)
               }
               DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                   DropdownMenuItem(text = { Text(text = "CentiMeter")},
                       onClick = {
                           oExpanded = false
                           outputUnity = "CentiMeter"
                           oconversionFactor.value = 0.01
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "Meters")},
                       onClick = {
                           oExpanded = false
                           outputUnity = "Meters"
                           oconversionFactor.value = 1.00
                           convertUnits()
                       })
                   DropdownMenuItem(text = { Text(text = "Feet")},
                       onClick = {  oExpanded = false
                           outputUnity = "Feet"
                           oconversionFactor.value = 0.3048
                           convertUnits() })
                   DropdownMenuItem(text = { Text(text = "MilliMeter")},
                       onClick = {  oExpanded = false
                           outputUnity = "MilliMeter"
                           oconversionFactor.value = 0.001
                           convertUnits() })
               }
           }
       }
       Spacer(modifier = Modifier.height(15.dp))
       Text(text = "Result $outputValue")
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}
