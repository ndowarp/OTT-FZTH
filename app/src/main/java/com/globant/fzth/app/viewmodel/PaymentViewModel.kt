package com.globant.fzth.app.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.fzth.app.events.PaymentEvents
import com.globant.fzth.app.states.PaymentState
import com.globant.fzth.domain.companions.Resource
import com.globant.fzth.domain.models.CreditCard
import com.globant.fzth.domain.usecases.ValidatePayment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val validatePayment: ValidatePayment
) : ViewModel() {
    private val _state = mutableStateOf(PaymentState())
    val state: State<PaymentState> = _state

    fun onEvent(event: PaymentEvents) {
        when (event) {
            is PaymentEvents.OnError -> _state.value = state.value.copy(
                errorMessage = event.message
            )
            is PaymentEvents.ValidatePayment -> {
                _state.value = state.value.copy(creditCard = event.payment)
                validatePayments(event.payment)
                Log.d("CREATION", event.payment.cardNumber)
            }
        }
    }

    private fun validatePayments(payment: CreditCard) = viewModelScope.launch {
        Log.d("CREATION", state.value.creditCard!!.cardNumber)
        if (!payment.cardNumber.isNullOrEmpty() && !payment.ownerName.isNullOrEmpty()) {
            validatePayment(payment).collect() { result ->
                when (result) {
                    is Resource.Error -> onEvent(PaymentEvents.OnError(result.message!!))
                    is Resource.Loading -> _state.value = state.value.copy(
                        isLoading = true
                    )
                    is Resource.Success -> {
                        _state.value = state.value.copy(creditCard = result.data!!)
                        onEvent(PaymentEvents.ValidatePayment(payment))
                    }
                }
            }
        }
    }
}