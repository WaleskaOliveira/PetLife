package br.edu.ifsp.scl.ads.pdm.petlife.controller

import br.edu.ifsp.scl.ads.pdm.petlife.UI.MainActivity
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetDao
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetSqliteImpl


class PetController(mainActivity: MainActivity) {

    private val petDao: PetDao = PetSqliteImpl(mainActivity)

    fun insertPet(pet: Pet) = petDao.createPet(pet)
    fun getPets(): MutableList<Pet> = petDao.retriverPets()
    fun updatePet(pet: Pet) = petDao.updatePet(pet)
    fun removePet(id: Int) = petDao.deletePet(id)
}