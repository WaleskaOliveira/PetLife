package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class EditarIdaAoPetShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarIdaAoPetShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarIdaAoPetshopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.salvarBt.setOnClickListener {
            val nomeCachorro = binding.nomeCachorroEt.text.toString()
            val novaData = binding.dataPetshopEt.text.toString()

            if (nomeCachorro.isNotEmpty() && novaData.isNotEmpty()) {
                val resultIntent = intent.apply {
                    putExtra("nomeCachorro", nomeCachorro)
                    putExtra("novaDataPetshop", novaData)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
