package com.example.android.roomwordssample

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.KeyStore

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Word::class], version = 6)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //wordDao.deleteAll()
            val True:Boolean = true
            val False:Boolean = false

            var word = Word("Harry Potter y la piedra filosofal" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", True, R.drawable.harry)
            wordDao.insert(word)

            word = Word("Harry Potter y la camara secreta" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", False, R.drawable.harry2)

            wordDao.insert(word)

            word = Word("Harry Potter y el prisionero de Askaban" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", True, R.drawable.harry3)
            wordDao.insert(word)

            word = Word("Harry Potter y el caliz de fuego" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", False, R.drawable.harry4)

            wordDao.insert(word)

            word = Word("Harry Potter y la orden del fenix" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", True, R.drawable.harry5)
            wordDao.insert(word)

            word = Word("Harry Potter y el principe mestizo" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", False, R.drawable.harry6)

            wordDao.insert(word)

            word = Word("Harry Potter y las reliquias de la muerte" , "J.K Rowling", "Editorial Salamandra", "Harry Potter y la piedra filosofal es el primer libro de una serie de siete, fue publicado en el Reino Unido el 30 de junio de 1997 y en español en marzo de 1999.\n" +
                    "\n" +
                    "Se trata de uno de los libros más vendidos de la historia, las estimaciones de sus ventas mundiales superan los 110 millones de copias.\n" +
                    "\n" +
                    "Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley.", True, R.drawable.harry7)

            wordDao.insert(word)

        }
    }

}
