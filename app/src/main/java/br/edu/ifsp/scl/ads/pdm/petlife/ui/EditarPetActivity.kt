package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEditPetBinding


class EditarPetActivity : AppCompatActivity() {
    private val aepb: ActivityEditPetBinding by lazy {
        ActivityEditPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aepb.root)


        val tiposPet = arrayOf("Dog", "Cat")
        val adapterTipo = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tiposPet)
        aepb.tipoPetSp.adapter = adapterTipo

        val portesPet = arrayOf("Small", "Medium", "Large")
        val adapterPorte = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, portesPet)
        aepb.portePetSp.adapter = adapterPorte

        val id = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("name") ?: ""
        val dateofbirth = intent.getStringExtra("dateofbirth") ?: ""
        val type = intent.getStringExtra("type") ?: ""
        val color = intent.getStringExtra("color") ?: ""
        val size = intent.getStringExtra("size") ?: ""

        aepb.nomePetEt.setText(name)
        aepb.dataNascimentoPetEt.setText(dateofbirth)
        aepb.tipoPetSp.setSelection(adapterTipo.getPosition(type))
        aepb.corPetEt.setText(color)
        aepb.portePetSp.setSelection(adapterPorte.getPosition(size))


        aepb.salvarBtn.setOnClickListener {
            if (validateFields()) {
                val intent = intent.apply {
                    putExtra("id", id)
                    putExtra("name", aepb.nomePetEt.text.toString())
                    putExtra("dateofbirth", aepb.dataNascimentoPetEt.text.toString())
                    putExtra("type", aepb.tipoPetSp.selectedItem.toString())
                    putExtra("color", aepb.corPetEt.text.toString())
                    putExtra("size", aepb.portePetSp.selectedItem.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    // valida os campos
    private fun validateFields(): Boolean {
        return when {
            aepb.nomePetEt.text.toString().isEmpty() -> {
                showError("Please fill in the name")
                false
            }
            aepb.dataNascimentoPetEt.text.toString().isEmpty() -> {
                showError("Date of birth is empty")
                false
            }
            aepb.tipoPetSp.selectedItem == null -> {
                showError("Select the type of pet")
                false
            }
            aepb.corPetEt.text.toString().isEmpty() -> {
                showError("Color is obrigatory")
                false
            }
            aepb.portePetSp.selectedItem == null -> {
                showError("Pet size must be selected")
                false
            }
            else -> true
        }

}

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}