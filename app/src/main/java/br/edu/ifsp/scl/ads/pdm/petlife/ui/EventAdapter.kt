package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event


class EventAdapter(context: Context, private val events: List<Event>) :
    ArrayAdapter<Event>(context, R.layout.activity_tile_event, events) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.activity_tile_event, parent, false)

        val event = events[position]
        view.findViewById<TextView>(R.id.typeTv).text = event.type
        view.findViewById<TextView>(R.id.dateTv).text = event.date
        view.findViewById<TextView>(R.id.eventDescriptionEt).text = event.description

        return view
    }
}