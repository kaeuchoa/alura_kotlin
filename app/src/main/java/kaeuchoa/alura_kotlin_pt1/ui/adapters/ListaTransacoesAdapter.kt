package kaeuchoa.alura_kotlin_pt1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat

class ListaTransacoesAdapter (listaTransacoes: List<Transacao>, context: Context) : BaseAdapter(){

    private val listaTransacoes = listaTransacoes
    private val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao : Transacao = listaTransacoes[position]



        view.transacao_data.text = transacao.data.formataParaBrasileiro()
        view.transacao_categoria.text = transacao.categoria
        view.transacao_valor.text = transacao.valor.toString()


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