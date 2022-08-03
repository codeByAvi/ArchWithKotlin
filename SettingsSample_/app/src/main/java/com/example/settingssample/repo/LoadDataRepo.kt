package com.example.settingssample.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.settingssample.Utlity
import com.example.settingssample.model.Root

class LoadDataRepo(val context: Context,val mutableLiveData: MutableLiveData<List<Root>>) {
    init {
        getData()
    }
    private fun getData(){
        if(Utlity.isNetworkConnected(context)){
            loadOnlineData()
        } else {
            loadOfflineData()
        }
    }

    private fun loadOnlineData(){
        OnlineMode(mutableLiveData)
    }

    private fun loadOfflineData(){
       OfflineRepo(context,mutableLiveData)
    }
}