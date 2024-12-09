package br.edu.ifsp.scl.ads.pdm.petlife.model

interface PetDao  {
    fun createPet(pet: Pet): Long
    //fun retriverPet(id: Int): Pet?
    fun retriverPets(): MutableList<Pet>
    fun updatePet(pet: Pet): Int
    fun deletePet(id: Int): Int
}