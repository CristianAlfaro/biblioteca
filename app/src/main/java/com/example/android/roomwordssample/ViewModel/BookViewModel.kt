package com.example.android.roomwordssample.ViewModel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.Entities.Book
import com.example.android.roomwordssample.Database.LibraryRoomDatabase
import com.example.android.roomwordssample.Repository.BookRepository
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Book>>
    val allFavorite: LiveData<List<Book>>

    init {
        val wordsDao = LibraryRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = BookRepository(wordsDao)
        allWords = repository.allWords
        allFavorite = repository.allFavoriteWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }
}
