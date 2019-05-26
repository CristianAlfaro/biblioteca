package com.example.android.roomwordssample.Adapter

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

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.android.roomwordssample.Entities.Book
import com.example.android.roomwordssample.R


abstract class BookListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<BookListAdapter.WordViewHolder>() {

    abstract fun addListener(holder: WordViewHolder, titulo:String, caratula:Int, favorito:String, autor:String, editorial:String, resumen:String, tags:String)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Book>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val autorTV: TextView = itemView.findViewById(R.id.textViewAutor)
        val caratulaIV: ImageView = itemView.findViewById(R.id.iv_caratula)
        val favoriteTV: TextView = itemView.findViewById(R.id.bt_favorite)
        val book_container:LinearLayout = itemView.findViewById(R.id.linear_layout_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.titulo
        holder.autorTV.text = current.autores
        holder.caratulaIV.setImageResource(current.caratula)
        var fav = ""
        if (current.favorito){
            holder.favoriteTV.text = "Quitar de favoritos"
            fav = "Quitar de favoritos"
            holder.favoriteTV.setOnClickListener {

            }
        } else {
            holder.favoriteTV.text = "Agregar a favoritos"
            fav = "Agregar a favoritos"
            holder.favoriteTV.setOnClickListener {

            }
        }

        addListener(holder,current.titulo,current.caratula,fav, current.autores, current.editorial, current.resumen, current.tags)

    }

    internal fun setWords(books: List<Book>) {
        this.words = books
        notifyDataSetChanged()
    }


    override fun getItemCount() = words.size
}


