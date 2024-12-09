package br.edu.ifsp.scl.ads.pdm.petlife.model

interface EventDao {
    fun createEvent(event: Event): Long
    fun retrieveEvents(petId: Int): MutableList<Event>
    fun updateEvent(event: Event): Int
    fun deleteEvent(eventId: Int): Int
}