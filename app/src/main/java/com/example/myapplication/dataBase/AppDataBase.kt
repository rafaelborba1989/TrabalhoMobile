package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Livro
import com.example.myapplication.repository.LivroDao

@Database(entities = [Livro::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun livroDao(): LivroDao
}