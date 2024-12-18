package br.edu.ifsp.scl.ads.pdm.petlife.controller

import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.EventDao
import br.edu.ifsp.scl.ads.pdm.petlife.model.EventSqliteImpl
import br.edu.ifsp.scl.ads.pdm.petlife.ui.EventosPetActivity



class EventController(eventosPetActivity: EventosPetActivity) {

    private val eventDao: EventDao = EventSqliteImpl(eventosPetActivity)

    fun addEvent(event: Event): Long = eventDao.createEvent(event)
    fun getEvents(petId: Int): MutableList<Event> = eventDao.retrieveEvents(petId)
    fun updateEvent(event: Event): Int = eventDao.updateEvent(event)
    fun removeEvent(eventId: Int): Int = eventDao.deleteEvent(eventId)
}