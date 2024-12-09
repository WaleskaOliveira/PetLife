package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityNovoEventoBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.EventViewModel

class AdicionarEventoActivity : AppCompatActivity() {

    private val aevb: ActivityNovoEventoBinding by lazy {
        ActivityNovoEventoBinding.inflate(layoutInflater)
    }

    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aevb.root)

        // Configurar spinner
        val eventTypes = arrayOf("Last visit to the Vet", "Last visit to the Petshop", "Last vaccine")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, eventTypes)
        aevb.eventTypeSp.adapter = adapter

        // Receber dados do intent
        val eventId = intent.getIntExtra("id", -1)
        val eventType = intent.getStringExtra("eventType") ?: ""
        val eventDate = intent.getStringExtra("eventDate") ?: ""
        val eventDescription = intent.getStringExtra("eventDescription") ?: ""

        // Configurar ViewModel
        eventViewModel.setEventData(eventType, eventDate, eventDescription)

        // Preencher os campos se for edição
        if (eventId != -1) {
            aevb.eventTypeSp.setSelection(adapter.getPosition(eventType))
            aevb.eventDateEt.setText(eventDate)
            aevb.eventDescriptionEt.setText(eventDescription)
        }

        // Observar erros de validação
        eventViewModel.validationError.observe(this, Observer { errorMessage ->
            errorMessage?.let { showError(it) }
        })

        aevb.saveEventBtn.setOnClickListener {
            // Atualizar dados no ViewModel
            eventViewModel.setEventData(
                aevb.eventTypeSp.selectedItem?.toString() ?: "",
                aevb.eventDateEt.text.toString(),
                aevb.eventDescriptionEt.text.toString()
            )

            if (eventViewModel.validateFields()) {
                val resultIntent = intent.apply {
                    putExtra("ID", eventId)
                    putExtra("eventType", aevb.eventTypeSp.selectedItem.toString())
                    putExtra("eventDate", aevb.eventDateEt.text.toString())
                    putExtra("eventDescription", aevb.eventDescriptionEt.text.toString())
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}