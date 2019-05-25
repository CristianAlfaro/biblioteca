package com.example.android.roomwordssample.Activities



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.roomwordssample.ViewModel.BookViewModel
import com.example.android.roomwordssample.R

class Main2Activity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



    }
}
/*
        val fab = findViewById<FloatingActionButton>(R.id.fav)
        fab.setOnClickListener {
            val intent = Intent(this@Main2Activity, NewBookActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Book(
                        data.getStringExtra(NewBookActivity.TITULO),
                        data.getStringExtra(NewBookActivity.AUTOR),
                        data.getStringExtra(NewBookActivity.EDITORIAL),
                        data.getStringExtra(NewBookActivity.RESUMEN),
                        data.getBooleanExtra(NewBookActivity.FAVORITO)
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