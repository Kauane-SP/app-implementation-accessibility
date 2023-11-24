package com.example.projetoacessibilidade.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetoacessibilidade.models.CardModel
import com.example.projetoacessibilidade.useCase.CardUseCase
import com.example.projetoacessibilidade.viewModel.events.CardTabEvent
import com.example.projetoacessibilidade.viewModel.states.CardTabState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CardViewModel(private val useCase: CardUseCase) : ViewModel() {

    private val event: MutableLiveData<CardTabEvent> = MutableLiveData()
    val viewEvent: LiveData<CardTabEvent> = event
    private val state: MutableLiveData<CardTabState> = MutableLiveData()
    val viewState: LiveData<CardTabState> = state
    private val viewModeJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModeJob

    fun init(cardModel: List<CardModel>) {
        getServicesCards(cardModel)
    }

    private fun getServicesCards(cardModel: List<CardModel>) {
        event.value = CardTabEvent.ShowCardLoading
        CoroutineScope(coroutineContext).launch {
            try {
                useCase.requestCards(::successInvokeList, ::errorInvokeList)
                state.value = CardTabState.GetServicesCard(cardModel)
            } catch (exception: Exception) {
                state.value = CardTabState.GetServicesCardError(exception.toString())
            }
        }
    }

    private fun successInvokeList(list: List<CardModel>?) {
        if (list != null) {
            state.value = CardTabState.GetServicesCard(list)
        }
    }

    private fun errorInvokeList(exeption: String) {
        state.value = CardTabState.GetServicesCardError(exeption)
    }
}