package br.edu.ifsp.scl.ads.pdm.petlife.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventViewModel : ViewModel() {

    private val _eventType = MutableLiveData<String>()
    val eventType: LiveData<String> get() = _eventType

    private val _eventDate = MutableLiveData<String>()
    val eventDate: LiveData<String> get() = _eventDate

    private val _eventDescription = MutableLiveData<String>()
    val eventDescription: LiveData<String> get() = _eventDescription

    private val _validationError = MutableLiveData<String?>()
    val validationError: LiveData<String?> get() = _validationError

    // Define os dados do evento
    fun setEventData(type: String, date: String, description: String) {
        _eventType.value = type
        _eventDate.value = date
        _eventDescription.value = description
    }

    // Valida os campos
    fun validateFields(): Boolean {
        return when {
            _eventType.value.isNullOrEmpty() -> {
                _validationError.value = "Please select the event type"
                false
            }
            _eventDate.value.isNullOrEmpty() -> {
                _validationError.value = "The date is obligatory"
                false
            }
            _eventDescription.value.isNullOrEmpty() -> {
                _validationError.value = "The description is obligatory"
                false
            }
            else -> {
                _validationError.value = null
                true
            }
        }
    }

}
