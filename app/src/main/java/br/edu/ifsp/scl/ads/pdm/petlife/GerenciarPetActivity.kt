package br.edu.ifsp.scl.ads.pdm.petlife


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityGerenciarPetBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet

class GerenciarPetActivity :  AppCompatActivity() {
    private lateinit var binding: ActivityGerenciarPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGerenciarPetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun salvarPetNaLista(){
        val nome = binding.nomePetEt.text.toString()
        val dataNascimento = binding.dataNascimentoEt.text.toString()
        val tipo = binding.tipoEt.text.toString()
        val cor = binding.corEt.text.toString()
        val porte = binding.porteEt.text.toString()
        val ultimaIdaPetShop = binding.ultimaIdaPetShopEt.text.toString()
        val ultimaIdaVeterinario = binding.ultimaIdaVeterinarioEt.text.toString()
        val ultimaIdaVacina = binding.ultimaIdaVacinaEt.text.toString()


        if (nome.isEmpty() || dataNascimento.isEmpty() || tipo.isEmpty() || cor.isEmpty()
            || porte.isEmpty() || ultimaIdaPetShop.isEmpty() || ultimaIdaVeterinario.isEmpty()
            || ultimaIdaVacina.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()}
        else {
            val novoPet = Pet(
                nome,
                dataNascimento,
                tipo,
                cor,
                porte,
                ultimaIdaPetShop,
                ultimaIdaVeterinario,
                ultimaIdaVacina,
            )

            limparCampos()
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun limparCampos(){
        binding.nomePetEt.text.clear()
        binding.dataNascimentoEt.text.clear()
        binding.tipoEt.text.clear()
        binding.corEt.text.clear()
        binding.porteEt.text.clear()
        binding.ultimaIdaPetShopEt.text.clear()
        binding.ultimaIdaVeterinarioEt.text.clear()
        binding.ultimaIdaVacinaEt.text.clear()
    }
    // private fun atualizarListaPets(){
    //   val stringBuilder = StringBuilder()
    //  listaPets.forEachIndexed{index, pet -> stringBuilder.append("Pet: ${index + 1}\n" +
    //          "Nome: ${pet.nome}\nData de Nascimento: ${pet.dataNascimento}\nTipo: ${pet.tipo}\n" +
    //          "Cor: ${pet.cor}\nPorte: ${pet.porte}\nÚltima Ida ao PetShop: ${pet.ultimaIdaPetShop}" +
    //          "\nÚltima Ida ao Veterinário: ${pet.ultimaIdaVeterinario}\nÚltima Ida para Vacina: " +
    //          "${pet.ultimaIdaVacina}\nTelefone do consultório:${pet.telefoneConsultorio}\n" +
    //          "Site do consultório: ${pet.siteConsultorio}\n\n")
    //  }
    //  binding.listaPetsTv.text = stringBuilder.toString()
}