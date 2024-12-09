package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.controller.EventController
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEventBinding

import br.edu.ifsp.scl.ads.pdm.petlife.model.Event

class EventosPetActivity : AppCompatActivity() {
    private val aeb: ActivityEventBinding by lazy {
        ActivityEventBinding.inflate(layoutInflater)
    }

    private val eventController by lazy { EventController(this) }
    private val eventList = mutableListOf<Event>()
    private val eventAdapter by lazy { EventAdapter(this, eventList) }

    private val addEventLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let {
                val eventId = it.getIntExtra("id", -1)
                val eventType = it.getStringExtra("eventType") ?: ""
                val eventDate = it.getStringExtra("eventDate") ?: ""
                val eventDescription = it.getStringExtra("eventDescription") ?: ""
                val eventHour = it.getStringExtra("eventHour") ?: ""
                val petId = intent.getIntExtra("id", -1)

                if (petId != -1) {
                    if (eventId == -1) {
                        eventController.addEvent(
                            Event(
                                petId = petId,
                                type = eventType,
                                date = eventDate,
                                description = eventDescription,
                                hour = eventHour
                            )
                        )
                    } else {
                        eventController.updateEvent(
                            Event(
                                petId = petId,
                                type = eventType,
                                date = eventDate,
                                description = eventDescription,
                                hour = eventHour
                            )
                        )
                    }
                    loadEvents(petId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aeb.root)

        val petName = intent.getStringExtra("name") ?: "Desconhecido"
        val petId = intent.getIntExtra("id", -1)

        setSupportActionBar(aeb.toolbarTb)
        supportActionBar?.title = getString(R.string.event_list)
        aeb.petNameTv.text = getString(R.string.events_of, petName)

        aeb.eventLv.adapter = eventAdapter
        registerForContextMenu(aeb.eventLv)

        if (petId != -1) {
            loadEvents(petId)
        }

        aeb.addEventBtn.setOnClickListener {
            val intent = Intent(this, AdicionarEventoActivity::class.java)
            addEventLauncher.launch(intent)
        }
    }

    private fun loadEvents(petId: Int) {
        eventList.clear()
        eventList.addAll(eventController.getEvents(petId))
        eventAdapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedEvent = eventList[info.position]

        return when (item.itemId) {
            R.id.editEventMi -> {
                val intent = Intent(this, AdicionarEventoActivity::class.java).apply {
                    putExtra("id", selectedEvent.id)
                    putExtra("eventType", selectedEvent.type)
                    putExtra("eventDate", selectedEvent.date)
                    putExtra("eventDescription", selectedEvent.description)
                    putExtra("eventHour", selectedEvent.hour) // Passa o horário para edição
                }
                addEventLauncher.launch(intent)
                true
            }
            R.id.removeEventMi -> {
                eventController.removeEvent(selectedEvent.id)
                loadEvents(selectedEvent.petId)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
