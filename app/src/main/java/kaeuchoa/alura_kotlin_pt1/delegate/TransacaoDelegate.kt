package kaeuchoa.alura_kotlin_pt1.delegate

import kaeuchoa.alura_kotlin_pt1.models.Transacao

interface TransacaoDelegate {
    fun delegate(transacaoCriada: Transacao)
}