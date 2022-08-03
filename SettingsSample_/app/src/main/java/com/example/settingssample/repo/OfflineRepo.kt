package com.example.settingssample.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.settingssample.model.Root
import com.example.settingssample.offline.AppDataBase
import com.example.settingssample.offline.DataDAO

class OfflineRepo(val context: Context,val liveData: MutableLiveData<List<Root>>) {
    init {
        initData()
    }
    fun initData(){
        val repo : DataDAO = AppDataBase.getInstance(context).todoData()
        liveData.postValue(repo.getAllData())
    }
}