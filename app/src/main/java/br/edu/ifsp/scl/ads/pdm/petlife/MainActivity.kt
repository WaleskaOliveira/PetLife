package br.edu.ifsp.scl.ads.pdm.petlife

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {

    private lateinit var amb: ActivityMainBinding
    private val listaPets = mutableListOf<Pet>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)

        amb.salvarPetBt.setOnClickListener {
            salvarPet()
        }




    data class Pet(
        val nome: String,
        val dataNascimento: String,
        val tipo: String,
        val cor: String,
        val porte: String,
        var ultimaIdaPetShop: String,
        var ultimaIdaVeterinario: String,
        var ultimaIdaVacina: String
    )

        private fun salvarPet() {
            val nome = amb.nomePetEt.text.toString()
            val dataNascimento = amb.dataNascimentoEt.text.toString()
            val tipo = amb.tipoEt.text.toString()
            val cor = amb.corEt.text.toString()
            val porte = amb.porteEt.text.toString()
            val ultimaIdaPetShop = amb.ultimaIdaPetShopEt.text.toString()
            val ultimaIdaVeterinario = amb.ultimaIdaVeterinarioEt.text.toString()
            val ultimaIdaVacina = amb.ultimaIdaVacinaEt.text.toString()

            if (nome.isEmpty() || dataNascimento.isEmpty() || tipo.isEmpty() || cor.isEmpty() || porte.isEmpty() ||
                ultimaIdaPetShop.isEmpty() || ultimaIdaVeterinario.isEmpty() || ultimaIdaVacina.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val pet = Pet(
                    nome,
                    dataNascimento,
                    tipo,
                    cor,
                    porte,
                    ultimaIdaPetShop,
                    ultimaIdaVeterinario,
                    ultimaIdaVacina
                )
                listaPets.add(pet)
                atualizarListaPets()
                limparCampos()
                Toast.makeText(this, "Pet salvo com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        private fun atualizarListaPets() {
            val stringBuilder = StringBuilder()
            listaPets.forEachIndexed { index, pet ->
                stringBuilder.append("Pet: ${index + 1}\nNome: ${pet.nome}\nData de Nascimento: ${pet.dataNascimento}\nTipo: ${pet.tipo}\nCor: ${pet.cor}\nPorte: ${pet.porte}\nÚltima Ida ao PetShop: ${pet.ultimaIdaPetShop}\nÚltima Ida ao Veterinário: ${pet.ultimaIdaVeterinario}\nÚltima Ida para Vacina: ${pet.ultimaIdaVacina}\n\n")
            }
            amb.listaPetsTv.text = stringBuilder.toString()
        }

        private fun limparCampos() {
            amb.nomePetEt.text.clear()
            amb.dataNascimentoEt.text.clear()
            amb.tipoEt.text.clear()
            amb.corEt.text.clear()
            amb.porteEt.text.clear()
            amb.ultimaIdaPetShopEt.text.clear()
            amb.ultimaIdaVeterinarioEt.text.clear()
            amb.ultimaIdaVacinaEt.text.clear()
        }
    }
}