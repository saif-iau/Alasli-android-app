package com.example.alasli.data

import android.content.Context
import androidx.room.Database
import androidx.room.Index
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alasli.data.entities.OrderEntity
import com.example.alasli.data.enums.EnumConverters

@Database(entities = [OrderEntity::class], version = 1)
@TypeConverters(EnumConverters::class)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                ).build().also { INSTANCE = it }
            }
    }
}
