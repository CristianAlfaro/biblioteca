package com.example.android.roomwordssample.Activities

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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.android.roomwordssample.R

/**
 * Activity for entering a word.
 */

class NewBookActivity : AppCompatActivity() {

    private lateinit var editTituloView: EditText
    private lateinit var editAutorView: EditText
    private lateinit var editResumenView: EditText
    private lateinit var editEditorialView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editTituloView = findViewById(R.id.edit_titulo)
        editAutorView = findViewById(R.id.edit_autor)
        editResumenView = findViewById(R.id.edit_resumen)
        editEditorialView = findViewById(R.id.edit_editorial)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTituloView.text) and TextUtils.isEmpty(editResumenView.text) and TextUtils.isEmpty(editEditorialView.text) and TextUtils.isEmpty(editAutorView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val titulo = editTituloView.text.toString()
                val autor = editAutorView.text.toString()
                val resumen = editResumenView.text.toString()
                val editorial = editEditorialView.text.toString()
                val favorito = true
                replyIntent.putExtra(TITULO, titulo)
                replyIntent.putExtra(AUTOR, autor)
                replyIntent.putExtra(RESUMEN, resumen)
                replyIntent.putExtra(EDITORIAL, editorial)
                replyIntent.putExtra(FAVORITO, favorito)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val TITULO = "titulo"
        const val AUTOR = "autor"
        const val RESUMEN = "resumen"
        const val FAVORITO= "favorito"
        const val EDITORIAL= "editorial"
    }
}

