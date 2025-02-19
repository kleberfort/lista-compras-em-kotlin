package livrokotlin.com.br

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import livrokotlin.com.br.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Gerencia a > criação da lista, exibição dos itens, e defini o layout
        val produtosAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1)

        binding.listViewProdutos.adapter = produtosAdapter


        // Configurando o botão para inserir produtos
        binding.btnInserir.setOnClickListener {
            val produto = binding.txtProduto.text.toString()

            if(produto.isNotBlank()){
                produtosAdapter.add(produto)
                binding.txtProduto.text.clear()
            }else{
                binding.txtProduto.error = "Preencha um valor"
            }
        }

        binding.listViewProdutos.setOnItemLongClickListener { adapterView, view, position, id ->

            //buscanto o item clicado
            val item = produtosAdapter.getItem(position)

            //removendo o item clicado da lista
            produtosAdapter.remove(item)

            true // Retorna true para indicar que o evento foi consumido
        }


    }
}