package com.example.settingssample.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settingssample.repo.LoadDataRepo
import com.example.settingssample.repo.OnlineMode

class ServiceViewModel(application: Application) : AndroidViewModel(application) {
    val mutableLiveData = MutableLiveData<List<Root>>()
    val application1:Application = application
    fun getData(): LiveData<List<Root>> {
        loadData()
        return mutableLiveData
    }

    fun loadData():LiveData<List<Root>>{
        LoadDataRepo( application1.applicationContext,mutableLiveData)
        return mutableLiveData
    }

    fun updateUI(): LiveData<List<Root>>{

        return mutableLiveData
    }

}