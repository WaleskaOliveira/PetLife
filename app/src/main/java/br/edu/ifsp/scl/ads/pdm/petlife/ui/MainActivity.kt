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
import br.edu.ifsp.scl.ads.pdm.petlife.controller.PetController
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet


class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private val PetController by lazy { PetController(this) }
    private val petList = mutableListOf<Pet>()
    private val petAdapter by lazy { PetAdapter(this, petList) }

    private val editPetLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let {
                val id = it.getIntExtra("id", -1)
                val name = it.getStringExtra("name") ?: ""
                val dateofbirth = it.getStringExtra("dateofbirth") ?: ""
                val type = it.getStringExtra("type") ?: ""
                val color = it.getStringExtra("color") ?: ""
                val size = it.getStringExtra("size") ?: ""

                if (id == -1) {
                    PetController.insertPet(
                        Pet(
                            name = name,
                            dateofbirth = dateofbirth,
                            type = type,
                            color = color,
                            size = size
                        )
                    )
                } else {
                    PetController.updatePet(
                        Pet(
                            id = id,
                            name = name,
                            dateofbirth = dateofbirth,
                            type = type,
                            color = color,
                            size = size
                        )
                    )
                }
                loadPets()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarTb)
        supportActionBar?.title = getString(R.string.app_name)

        amb.petLv.adapter = petAdapter
        loadPets()


        registerForContextMenu(amb.petLv)


        amb.petLv.setOnItemClickListener { _, _, position, _ ->
            val selectedPet = petList[position]
            val intent = Intent(this, EventosPetActivity::class.java).apply {
                putExtra("name", selectedPet.name)
            }
            startActivity(intent)
        }


        amb.addPetBtn.setOnClickListener {
            val intent = Intent(this, EditarPetActivity::class.java)
            editPetLauncher.launch(intent)
        }
    }


    private fun loadPets() {
        petList.clear()
        petList.addAll(PetController.getPets())
        petAdapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_pet, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedPet = petList[info.position]

        return when (item.itemId) {
            R.id.editPetMi-> {

                val intent = Intent(this, EditarPetActivity::class.java).apply {
                    putExtra("id", selectedPet.id)
                    putExtra("name", selectedPet.name)
                    putExtra("dateofbirth", selectedPet.dateofbirth)
                    putExtra("type", selectedPet.type)
                    putExtra("color", selectedPet.color)
                    putExtra("size", selectedPet.size)
                }
                editPetLauncher.launch(intent)
                true
            }
            R.id.removePetMi -> {
                PetController.removePet(selectedPet.id)
                loadPets()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}




