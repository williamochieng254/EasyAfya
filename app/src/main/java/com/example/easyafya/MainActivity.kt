package com.example.easyafya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

                        // HOME SCREEN

                        "home" -> {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),

                                onRequestVisitClick = {
                                    currentScreen = "request"
                                },

                                onHealthServicesClick = {
                                    currentScreen = "services"
                                },

                                onHealthTipsClick = {
                                    currentScreen = "tips"
                                }
                            )
                        }

                        // REQUEST VISIT SCREEN

                        "request" -> {
                            RequestVisitScreen(
                                modifier = Modifier.padding(innerPadding),

                                onBackClick = {
                                    currentScreen = "home"
                                },

                                onSubmitted = {
                                    currentScreen = "status"
                                }
                            )
                        }

                        // REQUEST STATUS SCREEN

                        "status" -> {
                            RequestStatusScreen(
                                modifier = Modifier.padding(innerPadding),

                                onBackClick = {
                                    currentScreen = "home"
                                }
                            )
                        }

                        // HEALTH SERVICES SCREEN

                        "services" -> {
                            HealthServicesScreen(
                                modifier = Modifier.padding(innerPadding),

                                onBackClick = {
                                    currentScreen = "home"
                                }
                            )
                        }

                        // HEALTH TIPS SCREEN

                        "tips" -> {
                            HealthTipsScreen(
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


// HOME SCREEN

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRequestVisitClick: () -> Unit,
    onHealthServicesClick: () -> Unit,
    onHealthTipsClick: () -> Unit
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

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Community Health Services",
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "How can we help you today?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // Request Health Visit button

        Button(
            onClick = onRequestVisitClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request Health Visit")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Services button

        Button(
            onClick = onHealthServicesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Health Services")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Tips button

        Button(
            onClick = onHealthTipsClick,

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Health Tips")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // My Requests button

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


// REQUEST HEALTH VISIT SCREEN

@Composable
fun RequestVisitScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSubmitted: () -> Unit
) {

    // Form fields

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var phone by rememberSaveable {
        mutableStateOf("")
    }

    var location by rememberSaveable {
        mutableStateOf("")
    }

    var healthNeed by rememberSaveable {
        mutableStateOf("")
    }

    var submitted by rememberSaveable {
        mutableStateOf(false)
    }


    // Android back button

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

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Enter your information below"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // Full Name

        OutlinedTextField(
            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Full Name")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Phone Number

        OutlinedTextField(
            value = phone,

            onValueChange = {
                phone = it
            },

            label = {
                Text("Phone Number")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Location

        OutlinedTextField(
            value = location,

            onValueChange = {
                location = it
            },

            label = {
                Text("Location")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Need

        OutlinedTextField(
            value = healthNeed,

            onValueChange = {
                healthNeed = it
            },

            label = {
                Text("Health Need")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // Submit button

        Button(
            onClick = {

                if (
                    name.isNotBlank() &&
                    phone.isNotBlank() &&
                    location.isNotBlank() &&
                    healthNeed.isNotBlank()
                ) {

                    submitted = true

                    onSubmitted()
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("REQUEST VISIT")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // Submission information

        if (submitted) {

            Text(
                text = "Request Submitted!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

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

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // Back button

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK TO HOME")
        }
    }
}


// REQUEST STATUS SCREEN

@Composable
fun RequestStatusScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    BackHandler {
        onBackClick()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Request Submitted!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Your community health visit request has been received.",
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // Status title

        Text(
            text = "STATUS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )


        // Current request status

        Text(
            text = "PENDING",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "A health worker will review your request.",
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )


        // Back to home

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK TO HOME")
        }
    }
}


// HEALTH SERVICES SCREEN

@Composable
fun HealthServicesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    BackHandler {
        onBackClick()
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Health Services",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Community Health Services",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // Service 1

        Text(
            text = "• Maternal and Child Health",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Service 2

        Text(
            text = "• Health Screening",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Service 3

        Text(
            text = "• Immunization Support",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Service 4

        Text(
            text = "• Health Education",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Service 5

        Text(
            text = "• Home Health Visits",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Service 6

        Text(
            text = "• Community Health Screening",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )


        // Back button

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK TO HOME")
        }
    }
}


// HEALTH TIPS SCREEN

@Composable
fun HealthTipsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    BackHandler {
        onBackClick()
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Health Tips",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Simple Community Health Tips",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // Health Tip 1

        Text(
            text = "• Wash your hands regularly with soap and clean water.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Tip 2

        Text(
            text = "• Drink safe and clean water throughout the day.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Tip 3

        Text(
            text = "• Eat a balanced diet with a variety of nutritious foods.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Tip 4

        Text(
            text = "• Keep your surroundings clean to help prevent infections.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // Health Tip 5

        Text(
            text = "• Seek professional medical help when you are unwell or concerned about your health.",
            fontSize = 17.sp
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )


        // Back button

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BACK TO HOME")
        }
    }
}