package com.example.arcitecturemvvm

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData

/**
 * Declares the DAO as a private property in the constructor. Pass in the DAO
 * instead of the whole database, because you only need access to the DAO
 * since the DAO contains all the read/write methods for the database.
 * There's no need to expose the entire database to the repository.
 * */


class WordRepository(private val wordDao: WordDao) {

   /**
    * Room executes all queries on a separate thread.
    * Observed LiveData will notify the observer when the data has changed.
    * */
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    /**
     * The suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
     * */
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }


    //addition
    suspend fun deleteAll() {
        wordDao.deleteAll()
    }

}

/**
 * What is a Repository?
 * A repository class abstracts access to multiple data sources.
 * The repository is not part of the Architecture Components libraries,
 * but is a suggested best practice for code separation and architecture.
 * A Repository class provides a clean API for data access to the rest of the application.
 * */


/**
 * Why use a Repository?
 * A Repository manages queries and allows you to use multiple backends.
 * In the most common example, the Repository implements the logic for deciding
 * whether to fetch data from a network or use results cached in a local database.
 * */