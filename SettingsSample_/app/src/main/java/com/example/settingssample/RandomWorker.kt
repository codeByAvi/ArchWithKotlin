package com.example.settingssample

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.settingssample.model.Root
import com.example.settingssample.model.ServiceViewModel
import com.example.settingssample.repo.SyncData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RandomWorker(val context: Context, val workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    val mutableLiveData = MutableLiveData<List<Root>>()

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        val randomNumber: Int = generateRandomNumber()
        try {

            SyncData(context,mutableLiveData)
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "syncing data $randomNumber", Toast.LENGTH_LONG)
                    .show()
            }
            Log.e("RandomWorker", "<>RandomWoker$randomNumber")
            try{
                Log.e("MainActivity instance","<><>"+MainActivity.activity)

//                val outputData: Data = Data.Builder()
//                    .put("data",mutableLiveData)
//                    .build()
//                return Result.success(outputData)
                GlobalScope.launch {
                    val syncData: SetSyncData = MainActivity.activity
                    syncData.syncData(mutableLiveData)
                }

            } catch (e: Exception){
                e.printStackTrace()
                Log.e("MainActivity instance","<><>"+e.message)
            }

            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Result.failure()
    }

    private fun generateRandomNumber(): Int {
        return (0..10).random()
    }

    private fun syncData() {

    }

    interface SetSyncData {
        fun syncData(mutableLiveData: MutableLiveData<List<Root>>)
    }

}