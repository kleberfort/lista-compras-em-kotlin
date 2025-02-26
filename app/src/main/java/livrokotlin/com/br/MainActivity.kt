package livrokotlin.com.br

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import livrokotlin.com.br.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
   // private lateinit var produtosAdapter: ProdutoAdapter2 // Criar o adapter globalmente

    private lateinit var produtosAdapter: ProdutoAdapter2

    val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Criar o adapter com a lista global de produtos
        produtosAdapter = ProdutoAdapter2(this, Utils.produtosGlobal)
        binding.listViewProdutos.adapter = produtosAdapter

        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

//        binding.btnAdicionar.setOnClickListener {
//            val chamada = Intent(Intent.ACTION_DIAL)
//
//            chamada.data = Uri.parse("tel:85997933415")
//
//            startActivity(chamada)
//        }


      //  produtosAdapter.addAll(Utils.produtosGlobal)


        binding.listViewProdutos.setOnItemLongClickListener { _, _, position, _ ->

            // Buscando o item clicado
            val item = produtosAdapter.getItem(position)
            if (item != null) {
                Utils.produtosGlobal.remove(item) // Removendo da lista global
                produtosAdapter.notifyDataSetChanged() // Notificando mudanças
                // Recalcular o total
                val soma = Utils.produtosGlobal.sumOf { it.valor * it.quantidade }
                binding.txtTotal.text = "TOTAL: ${f.format(soma)}"
            }

            true // Retorna true para indicar que o evento foi consumido
        }



    }



    override fun onResume() {
        super.onResume()
        produtosAdapter.notifyDataSetChanged() // Atualiza a lista ao retornar para a tela


        // Recalcular o total
        val soma = Utils.produtosGlobal.sumOf { it.valor * it.quantidade }
        binding.txtTotal.text = "TOTAL: ${f.format(soma)}"
    }
}
