package kaeuchoa.alura_kotlin_pt1.ui.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaMoedaBR
import kaeuchoa.alura_kotlin_pt1.extensions.limitaEmAte
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter (listaTransacoes: List<Transacao>, context: Context) : BaseAdapter(){

    private val listaTransacoes = listaTransacoes
    private val context = context

    private val LIMITE_CATEGORIA: Int = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao : Transacao = listaTransacoes[position]

        if(transacao.tipo == TipoTransacao.RECEITA){
            view.transacao_valor.setTextColor(ContextCompat.getColor(context,R.color.receita))
            view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }else{
            view.transacao_valor.setTextColor(ContextCompat.getColor(context,R.color.despesa))
            view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }


        view.transacao_data.text = transacao.data.formataParaBrasileiro()
        view.transacao_categoria.text = transacao.categoria.limitaEmAte(LIMITE_CATEGORIA)
        view.transacao_valor.text = transacao.valor.formataParaMoedaBR()


        return view
    }

    override fun getItem(position: Int): Transacao {
        return listaTransacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listaTransacoes.size
    }

}