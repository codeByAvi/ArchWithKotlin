package com.example.settingssample.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.settingssample.MainActivity
import com.example.settingssample.SettingsSampleApplication
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
import kotlinx.coroutines.coroutineScope
import java.lang.Appendable
import java.lang.Exception
import java.util.ArrayList

class OnlineMode(val liveData: MutableLiveData<List<Root>>) {
    var items: List<Root> = ArrayList<Root>()
    lateinit var mContext: Context

    constructor(context: Context, liveData: MutableLiveData<List<Root>>) : this(liveData) {
        mContext = context
    }

    init {
        initData()
    }

    fun initData() {
        val items: List<Root> = ArrayList()
        val apiService: ApiService = EndPoint.apiService()
        Log.e("ServiceViewModel", "<><>apiService<>${apiService.data}")
        var count: Int = 0
        apiService.data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Root>> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("ServiceViewModel", "<>onSubscribe<>")
                }

                override fun onNext(t: List<Root>) {
                    Log.e("ServiceViewModel", "<>onNext<>" + t.size)
                    liveData.postValue(t)
//                    items[count].name = t[count].name
//                    items[count].email = t[count].email
//                    items[count].id = t[count].id
//                    //setupOfflineData(t)
//                    count++
//                    setData(t)
                }

                override fun onError(e: Throwable) {
                    Log.e("ServiceViewModel", "<>onError<>" + e.message)
                }

                override fun onComplete() {
                    Log.e("ServiceViewModel", "<>onComplete<>")
                    syncData(items)
                }

            })
    }

    fun setData(listItems: List<Root>) {
        items = listItems;
    }

    fun getData(): List<Root> {
        return items
    }

    fun syncData(it: List<Root>) {
        try {
            val repo: DataDAO = AppDataBase.getInstance(mContext).todoData()
            for (i in 1 until it.size) {
                Log.e("Worker", "<>data inserted")
                repo.insertData(DataEntity(i, it[i].name, it[i].email))
            }
        } catch (e:Exception){
            e.printStackTrace()
        }

//        val observer: androidx.lifecycle.Observer<List<Root>> = androidx.lifecycle.Observer {
//            kotlin.run {
//                for (i in 1 until it.size) {
//                    Log.e("Worker", "<>data inserted")
//                    repo.insertData(DataEntity(i, it[i].name, it[i].email))
//                }
//            }
//        }
//        mutableLiveData.observe(this,observer)

    }

    fun setupOfflineData(items: List<Root>) {

        val context: Context = SettingsSampleApplication().applicationContext
        Log.e("Online mode", "<>Online mode<>$context")
        val db = Room.databaseBuilder(context, AppDataBase::class.java, "sample.db").build()
        for (i in 1..items.size) {
            Log.e("Onlinemode", "<>data inserted")
            db.todoData().insertData(DataEntity(i, items[i].name, items[i].email))
        }
    }
}