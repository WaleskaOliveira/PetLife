package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet



class GerenciarPetActivity(context: Context, private val pets: MutableList<Pet>) :
    ArrayAdapter<Pet>(context, R.layout.activity_tile_pet, pets) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_tile_pet, parent, false)
        val pet = pets[position]


        val nameTv = view.findViewById<TextView>(R.id.nameTv)
        val typeTv = view.findViewById<TextView>(R.id.typeTv)


        nameTv.text = pet.name
        typeTv.text = pet.type

        return view
    }
}