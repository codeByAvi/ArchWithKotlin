package com.example.settingssample

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService


class Utlity {
    companion object {

     fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager!!.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }
}


}