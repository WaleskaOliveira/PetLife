package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEditarPetBinding


class EditarPetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.salvarBt.setOnClickListener{
            val nomeCachorro = binding.nomeCachorroEt.text.toString()
            val dataNascimento = binding.dataNascimentoEt.text.toString()
            val tipo = binding.tipoEt.text.toString()
            val cor = binding.corEt.text.toString()
            val porte = binding.porteEt.text.toString()

            if (nomeCachorro.isNotEmpty() && dataNascimento.isNotEmpty() && tipo.isNotEmpty() && cor.isNotEmpty() && porte.isNotEmpty()){
                val resultIntent = intent.apply {
                    putExtra("nome", nomeCachorro)
                    putExtra("dataNascimento", dataNascimento)
                    putExtra("tipo", tipo)
                    putExtra("cor", cor)
                    putExtra("porte", porte)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
