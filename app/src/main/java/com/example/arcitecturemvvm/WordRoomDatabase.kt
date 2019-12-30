package com.example.arcitecturemvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class

/**
 * You annotate the class to be a Room database
 * with @Database and use the annotation parameters to declare
 * the entities that belong in the database and set the version number.
 * Each entity corresponds to a table that will be created in the database.
 * */
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)

/**
 * The database class for Room must be abstract and extend RoomDatabase
 * */
abstract class WordRoomDatabase : RoomDatabase() {

    /**
     * making database provides its DAOs by creating an abstract "getter" method for each @Dao
     * */
    abstract fun wordDao(): WordDao

    /**
     *  creating the callback within the WordRoomDatabase class:
     * */
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
//                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

//            var word = Word("Hello", "I love this")
//
//            wordDao.insert(word)
            /**
             * // Add sample words.
             * var word = Word("Hello")
             * wordDao.insert(word)
             * word = Word("World!")
             * wordDao.insert(word)*/

            // TODO: Add your own words!
        }
    }

    companion object {
        /**
         * Singleton WordRoomDatabase prevents multiple instances of database opening at the same time
         * */
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        /**
         * getDatabase returns the singleton. It'll create the database the first time it's accessed,
         * using Room's database builder to create a RoomDatabase object in the application context from
         * the WordRoomDatabase class and names it "word_database".
         * */
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        )
                : WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}

/**
 * What is a Room database?
 * Room is a database layer on top of an SQLite database.
 * Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 * Room uses the DAO to issue queries to its database.
 * By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the main thread.
 * When Room queries return LiveData, the queries are automatically run asynchronously on a background thread.
 * Room provides compile-time checks of SQLite statements.
 * */