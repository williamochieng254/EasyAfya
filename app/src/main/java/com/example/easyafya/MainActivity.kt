package com.example.easyafya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easyafya.ui.theme.EasyafyaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EasyafyaTheme {

                var currentScreen by rememberSaveable {
                    mutableStateOf("home")
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    when (currentScreen) {

                        "home" -> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                onRequestVisitClick = {
                                    currentScreen = "request"
                                }
                            )
                        }

                        "request" -> {
                            RequestVisitScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = {
                                    currentScreen = "home"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRequestVisitClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "EasyAfya",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Community Health Services",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "How can we help you today?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRequestVisitClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request Health Visit")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // We will build this next
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Health Services")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // We will build this next
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Health Tips")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // We will build this next
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("My Requests")
        }
    }
}

@Composable
fun RequestVisitScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var healthNeed by rememberSaveable { mutableStateOf("") }

    var submitted by rememberSaveable { mutableStateOf(false) }

    BackHandler {
        onBackClick()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Request Health Visit",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter your information below"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("Full Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = {
                Text("Phone Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = {
                Text("Location")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = healthNeed,
            onValueChange = { healthNeed = it },
            label = {
                Text("Health Need")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (
                    name.isNotBlank() &&
                    phone.isNotBlank() &&
                    location.isNotBlank() &&
                    healthNeed.isNotBlank()
                ) {
                    submitted = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("REQUEST VISIT")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (submitted) {

            Text(
                text = "Request Submitted!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = """
                    Name: $name
                    Phone: $phone
                    Location: $location
                    Health Need: $healthNeed
                """.trimIndent(),
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK TO HOME")
        }
    }
}