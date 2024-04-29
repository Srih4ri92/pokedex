package com.sri.pokedex.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
abstract class LocalDatabase {
    lateinit var db: PokedexDatabase

    @Before
    fun initDb(){
        db = Room
            .inMemoryDatabaseBuilder(getApplicationContext(), PokedexDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB(){
        db.close()
    }
}