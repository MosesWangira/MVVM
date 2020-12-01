package com.example.arcitecturemvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.arcitecturemvvm.data.model.Word


/**
 * @Dao annotation identifies it as a DAO class for Room.
 * */
@Dao
interface WordDao {
    //addition
    @Delete
    suspend fun delete(note : Word)


//    @Query("SELECT COUNT(id) FROM word_table")
//    suspend fun getCount()

//    @Query ("DELETE FROM word_table WHERE rowId = id")
//    suspend fun delete_one_row(id : Long)


    /**
     * fun getAlphabetizedWords(): List<Word>: A method to get all the words and have it return a List of Words.
     * @Query("SELECT * from word_table ORDER BY word ASC"): Query that returns a list of words sorted in ascending order.
     * */

    /**
     * LiveData, a lifecycle library class for data observation, solves the problem of data changes.
     * Use a return value of type LiveData in your method description,
     * and Room generates all necessary code to update the LiveData when the database is updated.
     * */
//    @Query("SELECT * from word_table ORDER BY urgency ASC")
    @Query("SELECT * from word_table")
    fun getAlphabetizedWords(): LiveData<List<Word>>


    /**
     * Declaring a suspend function to insert one word.
     * @Insert annotation is a special DAO method annotation where you don't have to provide any SQL!
     *
     * onConflict = OnConflictStrategy.IGNORE:
     * The selected on conflict strategy ignores a new word if it's exactly the same as one already in the list.
     *
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)


    /**
     * suspend fun deleteAll(): Declares a suspend function to delete all the words.
     * @Query("DELETE FROM word_table"):
     * @Query requires that you provide a SQL query as a string parameter to the annotation,
     * allowing for complex read queries and other operations.
     * */
    @Query("DELETE  FROM word_table")
    suspend fun deleteAll()


    /**
     * @Query update
     * */
    @Update
    abstract fun update(word: Word)
}


/**
 * DAO
 * In the DAO (data access object), you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL and generates queries from convenience annotations for common queries, such as @Insert.
 * Room uses the DAO to create a clean API for your code.
 * The DAO must be an interface or abstract class.
 * By default, all queries must be executed on a separate thread.
 * Room has coroutines support,
 * allowing your queries to be annotated with the suspend modifier and then called from a coroutine or from another suspension function.
 * */