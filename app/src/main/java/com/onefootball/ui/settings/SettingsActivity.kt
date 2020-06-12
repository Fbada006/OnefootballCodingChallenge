package com.onefootball.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onefootball.R

/**This activity will just host our [SettingsFragment]*/
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
}