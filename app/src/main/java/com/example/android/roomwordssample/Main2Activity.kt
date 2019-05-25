package com.example.android.roomwordssample



import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast

class Main2Activity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allFavorite.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })
    }
}
/*
        val fab = findViewById<FloatingActionButton>(R.id.fav)
        fab.setOnClickListener {
            val intent = Intent(this@Main2Activity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Word(
                        data.getStringExtra(NewWordActivity.TITULO),
                        data.getStringExtra(NewWordActivity.AUTOR),
                        data.getStringExtra(NewWordActivity.EDITORIAL),
                        data.getStringExtra(NewWordActivity.RESUMEN),
                        data.getBooleanExtra(NewWordActivity.FAVORITO)
                )
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}

private fun Intent.getBooleanExtra(favorito: String): Boolean {
    return favorito.toBoolean()
}
*/