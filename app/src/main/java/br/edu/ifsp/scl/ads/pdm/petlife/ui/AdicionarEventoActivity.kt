package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityNovoEventoBinding

class AdicionarEventoActivity : AppCompatActivity() {
    private val aevb: ActivityNovoEventoBinding by lazy {
        ActivityNovoEventoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aevb.root)


        val eventTypes = arrayOf("Last visit to the vet", "Last visit to the Petshop", "Last vaccine")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, eventTypes)
        aevb.eventTypeSp.adapter = adapter


        val eventId = intent.getIntExtra("id", -1)
        val eventType = intent.getStringExtra("eventType") ?: ""
        val eventDate = intent.getStringExtra("eventDate") ?: ""
        val eventDescription = intent.getStringExtra("eventDescription") ?: ""

        if (eventId != -1) {
            aevb.eventTypeSp.setSelection(adapter.getPosition(eventType))
            aevb.eventDateEt.setText(eventDate)
            aevb.eventDescriptionEt.setText(eventDescription)
        }


        aevb.saveEventBtn.setOnClickListener {
            if (validateFields()) {
                val eventTypeSelected = aevb.eventTypeSp.selectedItem.toString()
                val eventDateInput = aevb.eventDateEt.text.toString()
                val eventDescriptionInput = aevb.eventDescriptionEt.text.toString()


                val resultIntent = intent.apply {
                    putExtra("id", eventId)
                    putExtra("eventType", eventTypeSelected)
                    putExtra("eventDate", eventDateInput)
                    putExtra("eventDescription", eventDescriptionInput)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

    }


    private fun validateFields(): Boolean {
        return when {
            aevb.eventTypeSp.selectedItem == null -> {
                showError("Event type must be selected")
                false
            }
            aevb.eventDateEt.text.isNullOrEmpty() -> {
                showError("Date is obrigatory")
                false
            }
            aevb.eventDescriptionEt.text.isNullOrEmpty() -> {
                showError("Description is obrigatory")
                false
            }
            else -> true
        }
    }


    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
