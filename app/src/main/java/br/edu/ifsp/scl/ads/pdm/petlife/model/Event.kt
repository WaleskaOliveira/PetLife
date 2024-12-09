package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int = 0,
    val petId: Int,
    val type: String,
    val date: String,
    val description: String
):Parcelable