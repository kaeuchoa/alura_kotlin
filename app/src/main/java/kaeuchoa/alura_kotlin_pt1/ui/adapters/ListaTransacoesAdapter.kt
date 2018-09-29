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

class ListaTransacoesAdapter (private val listaTransacoes: List<Transacao>, private val context: Context) : BaseAdapter(){

    private val LIMITE_CATEGORIA: Int = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao : Transacao = listaTransacoes[position]

        adicionaValor(transacao,view)
        adicionaIcone(transacao, view)
        adicionaData(view, transacao)
        adicionaCategoria(view, transacao)

        return view
    }

    private fun adicionaIcone(transacao: Transacao, view: View) {
        val icone = getIconePorTipo(transacao.tipo)
        view.transacao_icone.setBackgroundResource(icone)
    }

    private fun getIconePorTipo(tipoTransacao: TipoTransacao): Int {
        return when(tipoTransacao){
            TipoTransacao.RECEITA -> R.drawable.icone_transacao_item_receita
            TipoTransacao.DESPESA -> R.drawable.icone_transacao_item_despesa
        }
    }

    private fun adicionaCategoria(view: View, transacao: Transacao) {
        view.transacao_categoria.text = transacao.categoria.limitaEmAte(LIMITE_CATEGORIA)
    }

    private fun adicionaData(view: View, transacao: Transacao) {
        view.transacao_data.text = transacao.data.formataParaBrasileiro()
    }


    private fun adicionaValor(transacao: Transacao, view: View){
        val cor = getCorPorTipo(transacao.tipo)
        view.transacao_valor.setTextColor(cor)
        view.transacao_valor.text = transacao.valor.formataParaMoedaBR()
    }

    private fun getCorPorTipo(tipoTransacao: TipoTransacao): Int {
        return when (tipoTransacao){
            TipoTransacao.RECEITA -> ContextCompat.getColor(context, R.color.receita)
            TipoTransacao.DESPESA -> ContextCompat.getColor(context, R.color.despesa)
        }
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