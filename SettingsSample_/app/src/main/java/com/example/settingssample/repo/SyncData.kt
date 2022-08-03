package com.example.settingssample.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.settingssample.api.ApiService
import com.example.settingssample.api.EndPoint
import com.example.settingssample.model.Root
import com.example.settingssample.offline.AppDataBase
import com.example.settingssample.offline.DataDAO
import com.example.settingssample.offline.DataEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.ArrayList

class SyncData(val context: Context,val data: MutableLiveData<List<Root>>) {

    init {
        initData()
    }

    fun initData() {
        var items: List<Root> = ArrayList(100)
        val apiService: ApiService = EndPoint.apiService()
        Log.e("SyncData", "<><>apiService<>${apiService.data}")
        var count: Int = 0
        apiService.data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Root>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("SyncData", "<>onSubscribe<>")
                }

                override fun onNext(t: List<Root>) {
                    Log.e("SyncData", "<>onNext<>" + t[0].name)
                    data.postValue(t)
                    items = t
                    count++
                }

                override fun onError(e: Throwable) {
                    Log.e("SyncData", "<>onError<>" + e.message)
                }

                override fun onComplete() {
                    Log.e("SyncData", "<>onComplete<>")
                    syncData(items)
                }

            })
    }

    fun syncData(it: List<Root>) {
        try {
            val repo: DataDAO = AppDataBase.getInstance(context).todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                repo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e: Exception){
            e.printStackTrace()
        }

    }
}