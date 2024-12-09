package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val id: Int = 0,
    val name: String = "",
    val dateofbirth: String = "",
    val type: String =  "",
    val color: String =  "",
    val size: String =  ""
):Parcelable
