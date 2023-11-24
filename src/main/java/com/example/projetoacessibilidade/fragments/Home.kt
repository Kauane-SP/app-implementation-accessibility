package com.example.projetoacessibilidade.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.adapter.CardAdapter
import com.example.projetoacessibilidade.models.CardModel
import com.example.projetoacessibilidade.repository.CardRepository
import com.example.projetoacessibilidade.useCase.CardUseCase
import com.example.projetoacessibilidade.viewModel.CardViewModel
import com.example.projetoacessibilidade.viewModel.states.CardTabState
import com.example.projetoacessibilidade.viewModelFactory.CardViewModelFactory

class Home : Fragment() {

    private lateinit var recyclerList: RecyclerView
    private lateinit var viewModel: CardViewModel
    private lateinit var listCards: List<CardModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerList = requireView().findViewById(R.id.recycler_cards)
        val context = activity as Context
        listCards = listOf()
        initViewModel(context, listCards)
    }

    fun initViewModel(context: Context, list: List<CardModel>) {
        val repository = CardRepository()
        val useCase = CardUseCase(repository)

        viewModel = ViewModelProvider(
            this,
            CardViewModelFactory(useCase)
        ).get(CardViewModel::class.java)
        initObserverState(context, list)
    }

    private fun initObserverState(context: Context, list: List<CardModel>) {
        viewModel.init(list)
        viewModel.viewState.observe(viewLifecycleOwner, Observer { status ->
            status.let {
                when (it) {
                    is CardTabState.GetServicesCard -> {
                        fillService(it.cartoes, context)
                    }
                    is CardTabState.GetServicesCardError -> {
//                        onServiceError(it.exception)
                    }
                }
            }
        })
    }

    private fun fillService(services: List<CardModel>, activity: Context) {
        recyclerList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerList.adapter = CardAdapter(services, activity)
    }

    companion object {
        fun newInstance() = Home()
    }

}