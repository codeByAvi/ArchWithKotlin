package com.example.settingssample

import android.app.Application
import android.content.Context

class SettingsSampleApplication:Application() {

        override fun getApplicationContext():Context{
            return this
        }


}