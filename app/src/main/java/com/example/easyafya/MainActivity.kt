
package com.example.easyafya

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onCreate")

        // Initialize persistent storage before fragments access saved requests.
        PatientRepository.initialize(applicationContext)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EASYAFYA_LIFECYCLE", "MainActivity: onDestroy")
    }
}
