package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEditarUltimaVacinaBinding


class EditarIdaParaVacinaActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditarUltimaVacinaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarUltimaVacinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.salvarBt.setOnClickListener {
            val nomeCachorro = binding.nomeCachorroEt.text.toString()
            val novaData = binding.dataVacinaEt.text.toString()

            if (nomeCachorro.isNotEmpty() && novaData.isNotEmpty()) {
                val resultIntent = intent.apply {
                    putExtra("nomeCachorro", nomeCachorro)
                    putExtra("novaDataVacina", novaData)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
