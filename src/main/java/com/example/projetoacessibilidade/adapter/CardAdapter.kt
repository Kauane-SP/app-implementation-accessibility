package com.example.projetoacessibilidade.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.adapter.viewHolder.CardViewHolder
import com.example.projetoacessibilidade.adapter.viewHolder.CardViewHolderTwo
import com.example.projetoacessibilidade.models.CardModel

class CardAdapter(private var list: List<CardModel>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CARD_ONE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cartao, parent, false)
                return CardViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cartao_two, parent, false)
                return CardViewHolderTwo(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CARD_ONE -> {
                val viewHolder = holder as CardViewHolder
                viewHolder.onBind(list[position])
            }
            else -> {
                val viewHolder = holder as CardViewHolderTwo
                viewHolder.onBind(list[position])
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].id % 2 == 0) {
            CARD_TWO
        } else {
            CARD_ONE
        }
    }

    companion object {
        private const val CARD_ONE = 1
        private const val CARD_TWO = 0
    }
}