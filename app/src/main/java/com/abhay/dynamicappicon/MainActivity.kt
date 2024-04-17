package com.abhay.dynamicappicon


import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val mainActivity = BuildConfig.main_activity
    private val mainActivityAlias = BuildConfig.main_activity_alias

    private var changeAppIconToOld = false
    private var changeAppIconToNew = false

    private lateinit var prefsManager: SharedPrefsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefsManager = SharedPrefsManager(this)

        val btnOld = findViewById<Button>(R.id.btnOld)
        val btnNew = findViewById<Button>(R.id.btnNew)
        btnOld.setOnClickListener {
            if (!prefsManager.getBoolean("isOldIconEnabled",true)){

                prefsManager.saveBoolean("isOldIconEnabled",true)
                prefsManager.saveBoolean("isNewIconEnabled",false)
                changeAppIconToOld = true
                Log.d("Icon Change", "old icon click ")
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    // This code will be executed if the API level is 28 (Android 9 Pie) or lower
                    changeEnabledComponent(
                        enabled = mainActivity,
                        disabled = mainActivityAlias
                    )
                    Toast.makeText(this, "Enabled Old Icon", Toast.LENGTH_LONG).show()
                }
            }

        }
        btnNew.setOnClickListener {
            if (!prefsManager.getBoolean("isNewIconEnabled",false)) {
                prefsManager.saveBoolean("isOldIconEnabled",false)
                prefsManager.saveBoolean("isNewIconEnabled",true)

                changeAppIconToNew = true
                Log.d("Icon Change", "new icon click ")

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    // This code will be executed if the API level is 28 (Android 9 Pie) or lower
                    changeEnabledComponent(
                        enabled = mainActivityAlias,
                        disabled = mainActivity
                    )
                    Toast.makeText(this, "Enabled New Icon", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    override fun onStop() {
        super.onStop()



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // This code will be executed if the API level is 29 (Android 10 Quince Start) or higher

            Log.d("Icon Change", "onStop: ")
            Log.d("Icon Change", "New ->: ${changeAppIconToNew}\n Old -> ${changeAppIconToOld}")

            if (changeAppIconToNew){
                changeEnabledComponent(
                    enabled = mainActivityAlias,
                    disabled = mainActivity
                )
            }

            if (changeAppIconToOld){
                changeEnabledComponent(
                    enabled = mainActivity,
                    disabled = mainActivityAlias
                )
            }
        }

    }

    private fun changeEnabledComponent(
        enabled: String,
        disabled: String,
    ) {
        packageManager.setComponentEnabledSetting(
            ComponentName(
                this@MainActivity,
                enabled
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packageManager.setComponentEnabledSetting(
            ComponentName(
                this@MainActivity,
                disabled
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}