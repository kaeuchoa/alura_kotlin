package kaeuchoa.alura_kotlin_pt1.models

import java.math.BigDecimal

class Resumo (private val transacoes : List<Transacao>) {

    fun valorReceita() : BigDecimal{
        var receitaTotal = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == TipoTransacao.RECEITA) {
                receitaTotal = receitaTotal.plus(transacao.valor)
            }
        }
        return receitaTotal
    }

    fun valorDespesa(): BigDecimal{
        var despesaTotal = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == TipoTransacao.DESPESA) {
                despesaTotal = despesaTotal.plus(transacao.valor)
            }
        }

        return despesaTotal
    }

    fun calculaTotal(): BigDecimal {
        return valorReceita().subtract(valorDespesa())
    }

}