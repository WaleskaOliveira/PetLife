package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEditarIdaVeterinarioBinding


class EditarIdaAoVeterinarioActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditarIdaVeterinarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarIdaVeterinarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.salvarBt.setOnClickListener {
            val nomeCachorro = binding.nomeCachorroEt.text.toString()
            val novaData = binding.dataVeterinarioEt.text.toString()

            if (nomeCachorro.isNotEmpty() && novaData.isNotEmpty()) {
                val resultIntent = intent.apply {
                    putExtra("nomeCachorro", nomeCachorro)
                    putExtra("novaDataVeterinario", novaData)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
