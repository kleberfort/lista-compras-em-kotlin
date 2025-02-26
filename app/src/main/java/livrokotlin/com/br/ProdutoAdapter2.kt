package livrokotlin.com.br

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class ProdutoAdapter2(contexto:Context, private val produtos: List<Produto>) : ArrayAdapter<Produto>(contexto, 0, produtos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


       // val v: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)

        val v:View

        if(convertView != null){
            v = convertView
        }else{
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        val item = getItem(position) ?: return v // Evita erro se o item for nulo


        // Certifique-se de que os IDs abaixo existem no seu XML
        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd) // ID corrigido
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))


        // Evita erro caso alguma view seja nula
        txt_produto.text = item.nome
        txt_qtd.text = item.quantidade.toString()
        txt_valor.text = f.format(item.valor)

        if(item.foto != null){
            img_produto?.setImageBitmap(item.foto)
        }else{
            //     img_produto?.setImageResource(R.drawable.imagem_padrao) // Define imagem padrão se necessário
        }

        return  v

    }

}