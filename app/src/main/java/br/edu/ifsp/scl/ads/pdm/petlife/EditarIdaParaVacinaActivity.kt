package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class EditarIdaParaVacinaActivity : AppCompatActivity() {


    private lateinit var binding: ActivityEditarIdaParaVacinaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarIdaParaVacinaBinding.inflate(layoutInflater)
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
