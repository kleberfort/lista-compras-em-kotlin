package livrokotlin.com.br

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import livrokotlin.com.br.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    //val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null
    private lateinit var binding: ActivityCadastroBinding

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Configurando o botão para inserir produtos
        binding.btnInserir.setOnClickListener {

            val produto = binding.txtProduto.text.toString()
            val qtd = binding.txtQtd.text.toString()
            val valor = binding.txtValor.text.toString()

            if(produto.isNotBlank() && qtd.isNotBlank() && valor.isNotBlank()){
                val prod = Produto(produto, qtd.toInt(), valor.toDouble(), imageBitMap)

                Utils.produtosGlobal.add(prod)
                // Finaliza a tela e volta para MainActivity
                finish()

                binding.txtProduto.text.clear()
                binding.txtQtd.text.clear()
                binding.txtValor.text.clear()
            }else{
                binding.txtProduto.error = if(binding.txtProduto.text.isEmpty()) "Preencha o nome do produto " else null
                binding.txtQtd.error = if(binding.txtQtd.text.isEmpty()) "Preencha a quantidade " else null
                binding.txtValor.error = if(binding.txtValor.text.isEmpty()) "Preencha o valor  " else null
            }
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if(result.resultCode == Activity.RESULT_OK){
                result.data?.data?.let { uri ->
                    val inputStream = contentResolver.openInputStream(uri)
                    inputStream?.use {
                        imageBitMap = BitmapFactory.decodeStream(it)
                        binding.imgFotoProduto.setImageBitmap(imageBitMap)
                    }
                }
            }
        }


        binding.imgFotoProduto.setOnClickListener {
            abrirGaleria()
        }

    }



    fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        imagePickerLauncher.launch(Intent.createChooser(intent, "Selecione uma imagem"))
    }

}