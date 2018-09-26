package kaeuchoa.alura_kotlin_pt1.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kaeuchoa.alura_kotlin_pt1.R

class ListaTransacoesAdapter (listaTransacoes : List<String>, context: Context) : BaseAdapter(){

    private val listaTransacoes = listaTransacoes
    private val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.transacao_item,parent,false)
    }

    override fun getItem(position: Int): Any {
        return listaTransacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listaTransacoes.size
    }

}