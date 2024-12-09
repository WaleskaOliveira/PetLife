package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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

        // Adicionar opções ao spinner de tipos de evento
        val eventTypes = arrayOf("Last visit to the vet", "Last visit to the Petshop", "Last vaccine", "Remedio")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, eventTypes)
        aevb.eventTypeSp.adapter = adapter

        // Configurar listener para o Spinner
        aevb.eventTypeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedType = eventTypes[position]
                // Mostrar ou esconder o campo de horário com base no tipo de evento
                if (selectedType == "Remedio") {
                    aevb.eventHourEt.visibility = View.VISIBLE
                } else {
                    aevb.eventHourEt.visibility = View.GONE
                    aevb.eventHourEt.text.clear() // Limpa o campo se for escondido
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Recuperar dados do evento enviado pela Intent
        val eventId = intent.getIntExtra("id", -1)
        val eventType = intent.getStringExtra("eventType") ?: ""
        val eventDate = intent.getStringExtra("eventDate") ?: ""
        val eventHour = intent.getStringExtra("eventHour") ?: "" // Novo campo de horário
        val eventDescription = intent.getStringExtra("eventDescription") ?: ""

        // Se o evento já existir, preencher os campos
        if (eventId != -1) {
            aevb.eventTypeSp.setSelection(adapter.getPosition(eventType))
            aevb.eventDateEt.setText(eventDate)
            aevb.eventDescriptionEt.setText(eventDescription)
            if (eventType == "Remedio") {
                aevb.eventHourTv.visibility = View.VISIBLE
                aevb.eventHourTv.setText(eventHour)
                aevb.eventHourEt.visibility = View.VISIBLE
                aevb.eventHourEt.setText(eventHour) // Preencher o campo de horário
            }
        }

        // Configurar botão de salvar
        aevb.saveEventBtn.setOnClickListener {
            if (validateFields()) {
                val eventTypeSelected = aevb.eventTypeSp.selectedItem.toString()
                val eventDateInput = aevb.eventDateEt.text.toString()
                val eventHourInput = if (eventTypeSelected == "Remedio") {
                    aevb.eventHourEt.text.toString()
                } else {
                    ""
                }
                val eventDescriptionInput = aevb.eventDescriptionEt.text.toString()

                // Enviar dados do evento pela Intent
                val resultIntent = intent.apply {
                    putExtra("id", eventId)
                    putExtra("eventType", eventTypeSelected)
                    putExtra("eventDate", eventDateInput)
                    putExtra("eventHour", eventHourInput) // Novo campo de horário
                    putExtra("eventDescription", eventDescriptionInput)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    // Validação dos campos
    private fun validateFields(): Boolean {
        return when {
            aevb.eventTypeSp.selectedItem == null -> {
                showError("Event type must be selected")
                false
            }
            aevb.eventDateEt.text.isNullOrEmpty() -> {
                showError("Date is mandatory")
                false
            }
            aevb.eventTypeSp.selectedItem.toString() == "Remedio" && aevb.eventHourEt.text.isNullOrEmpty() -> { // Validação do horário
                showError("Hour is mandatory for medicine events")
                false
            }
            aevb.eventDescriptionEt.text.isNullOrEmpty() -> {
                showError("Description is mandatory")
                false
            }
            else -> true
        }
    }

    // Mostrar mensagem de erro
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

