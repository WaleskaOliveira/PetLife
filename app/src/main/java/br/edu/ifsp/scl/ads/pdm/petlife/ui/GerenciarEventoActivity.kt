package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event


class GerenciarEventoActivity(context: Context, private val events: MutableList<Event>) :
    ArrayAdapter<Event>(context, R.layout.activity_tile_event, events) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_tile_event, parent, false)
        val event = events[position]

        val typeTv = view.findViewById<TextView>(R.id.typeTv)
        val dateTv = view.findViewById<TextView>(R.id.dateTv)

        typeTv.text = event.type
        dateTv.text = event.date

        return view
    }
}