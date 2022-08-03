package com.example.settingssample.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataEntity")
data class DataEntity(@PrimaryKey(autoGenerate = true) var id:Int,
                      @ColumnInfo(name = "name") var name:String,
            @ColumnInfo(name = "email") var email:String)
