package kaeuchoa.alura_kotlin_pt1.extensions.dao

import kaeuchoa.alura_kotlin_pt1.models.Transacao

class TransacaoDAO {

    val listaTransacoes : List<Transacao> = Companion.listaTransacoes
    companion object {
        private val listaTransacoes : MutableList<Transacao> = mutableListOf()
    }



    fun adiciona(transacao: Transacao){
        Companion.listaTransacoes.add(transacao)
    }

    fun remove(posicao: Int){
        Companion.listaTransacoes.removeAt(posicao)
    }

    fun altera(transacao: Transacao, posicao: Int){
        Companion.listaTransacoes[posicao] = transacao
    }

}