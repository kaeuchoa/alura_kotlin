package kaeuchoa.alura_kotlin_pt1.models

import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPorTipo(TipoTransacao.RECEITA)

    val despesa get() = somaPorTipo(TipoTransacao.DESPESA)

    val total get() = receita.subtract(despesa)

    private fun somaPorTipo(tipo : TipoTransacao) : BigDecimal{
        val somaTransacoes: Double = transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaTransacoes)

    }



}