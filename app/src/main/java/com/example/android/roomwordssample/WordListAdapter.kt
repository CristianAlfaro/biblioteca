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

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class WordListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val autorTV: TextView = itemView.findViewById(R.id.textViewAutor)
        val caratulaIV: ImageView = itemView.findViewById(R.id.iv_caratula)
        val favoriteTV: TextView = itemView.findViewById(R.id.bt_favorite)

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
        if (current.favorito){
            holder.favoriteTV.text = "Quitar de favoritos"
            holder.favoriteTV.setOnClickListener {

            }
        } else {
            holder.favoriteTV.text = "Agregar a favoritos"
            holder.favoriteTV.setOnClickListener {

            }
        }

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }


    override fun getItemCount() = words.size
}


