package com.example.projetoacessibilidade.adapter.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.models.CardModel

class CardViewHolderTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val numberCard : AppCompatTextView = itemView.findViewById(R.id.number_card)
    private val data: AppCompatTextView = itemView.findViewById(R.id.data_card)
    private val name: AppCompatTextView = itemView.findViewById(R.id.tv_name)

    fun onBind(cardModel: CardModel){

        numberCard.text = cardModel.numberCard
        data.text = cardModel.codeCard
        name.text = cardModel.name
    }
}