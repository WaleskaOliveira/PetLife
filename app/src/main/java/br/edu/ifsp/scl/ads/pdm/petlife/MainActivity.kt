package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent



import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_INTENT
import android.content.Intent.EXTRA_TITLE
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet


class MainActivity : AppCompatActivity() {

    private lateinit var amb: ActivityMainBinding
    private val listaPets = mutableListOf<Pet>()
    private lateinit var barl: ActivityResultLauncher<Intent>
    private lateinit var editarIdaVeterinarioLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarIdaPetshopLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarIdaVacinaLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarPetLauncher: ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)
        //amb.salvarPetBt.setOnClickListener {
        //salvarPetNaLista()
        //}'
        amb.editarIdaVeterinarioBt.setOnClickListener {
            editarIdaVeterinario()
        }
        amb.editarIdaPetshopBt.setOnClickListener {
            editarIdaPetshop()
        }
        amb.editarIdaVacinaBt.setOnClickListener {
            editarIdaVacina()
        }
        amb.editarPetBt.setOnClickListener {
            editarPet()
        }


        editarIdaVeterinarioLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == RESULT_OK) {
                val nomePet = result.data?.getStringExtra("nomePet")
                val novaData = result.data?.getStringExtra("novaDataVeterinario")
                val novoTelefone = result.data?.getStringExtra("novoTelefoneConsultorio")
                val novoSite = result.data?.getStringExtra("novoSiteConsultorio")
                if (nomePet != null && novaData != null && novoTelefone != null && novoSite !=null ) {
                    atualizarDataVeterinario(nomePet, novaData,novoTelefone,novoSite)
                }
            }
        }
        editarIdaPetshopLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if (result.resultCode == RESULT_OK){
                val nomePet = result.data?.getStringExtra("nomePet")
                val novaData = result.data?.getStringExtra("novaDataPetshop")
                if (nomePet != null && novaData != null){
                    atualizarDataPetshop(nomePet, novaData)
                }
            }
        }
        editarIdaVacinaLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val nomePet = result.data?.getStringExtra("nomePet")
                val novaData = result.data?.getStringExtra("novaDataVacina")
                if (nomePet != null && novaData != null) {
                    atualizarDataVacina(nomePet, novaData)
                }
            }
        }
        editarPetLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if (result.resultCode == RESULT_OK){
                val nomePet = result.data?.getStringExtra("nomePet")
                val novoNomePet = result.data?.getStringExtra("novoNomePet")
                val novaDataNascimento = result.data?.getStringExtra("novaDataNascimento")
                val novoTipo = result.data?.getStringExtra("novoTipo")
                val novaCor = result.data?.getStringExtra("novaCor")
                val novoPorte = result.data?.getStringExtra("novoPorte")
                if (nomePet != null && novoNomePet != null && novaDataNascimento != null
                    && novoTipo != null && novaCor != null && novoPorte != null) {
                    atualizarPet(nomePet, novoNomePet, novaDataNascimento, novoTipo, novaCor, novoPorte)
                }
            }
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.addPetMi -> {
            barl.launch(Intent(this,GerenciarPetActivity::class.java))
            true
        }
        else ->{
            false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) = menuInflater.inflate(R.menu.context_menu_main,menu)



    private fun editarIdaVeterinario() {
        val intent = Intent(this, EditarIdaAoVeterinarioActivity::class.java)
        editarIdaVeterinarioLauncher.launch(intent)
    }
    private fun atualizarDataVeterinario(nomePet: String, novaData: String, novoTelefone: String, novoSite: String) {
        val pet = listaPets.find { it.nome == nomePet }
        pet?.let {
            it.ultimaIdaVeterinario = novaData
            it.telefoneConsultorio = novoTelefone
            it.siteConsultorio = novoSite
            //Atualizar no banco
            Toast.makeText(this, "Data de ida ao Veterinario atualizada!", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Pet não encontrado!", Toast.LENGTH_SHORT).show()
    }
    private fun editarIdaPetshop(){
        val intent = Intent(this, EditarIdaAoPetShopActivity::class.java)
        editarIdaPetshopLauncher.launch(intent)
    }
    private fun atualizarDataPetshop(nomePet: String, novaData: String) {
        val pet = listaPets.find { it.nome == nomePet }
        pet?.let {
            it.ultimaIdaPetShop = novaData
            //Atualizar no banco
            Toast.makeText(this, "Data de ida ao Petshop atualizada!", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Pet não encontrado!", Toast.LENGTH_SHORT).show()
    }
    private fun editarIdaVacina(){
        val intent = Intent(this, EditarIdaParaVacinaActivity::class.java)
        editarIdaVacinaLauncher.launch(intent)
    }
    private fun atualizarDataVacina(nomePet: String, novaData: String) {
        val pet = listaPets.find { it.nome == nomePet }
        pet?.let {
            it.ultimaIdaVacina = novaData
            //Atualizar no banco
            Toast.makeText(this, "Data de ida para vacina atualizada!", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Pet não encontrado!", Toast.LENGTH_SHORT).show()
    }
    private fun atualizarPet(nomePet: String,novoNomePet : String, novaDataNascimento : String, novoTipo : String,
                             novaCor : String, novoPorte : String){
        val pet = listaPets.find { it.nome == nomePet }
        pet?.let {
            it.nome = novoNomePet
            it.dataNascimento = novaDataNascimento
            it.tipo = novoTipo
            it.cor = novaCor
            it.porte = novoPorte
            //atualiza o banco
            Toast.makeText(this, "Pet atualizado!", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Pet não encontrado!", Toast.LENGTH_SHORT).show()
    }
    private fun editarPet(){
        val intent = Intent(this, EditarPetActivity::class.java)
        editarPetLauncher.launch(intent)
    }
    }
}




