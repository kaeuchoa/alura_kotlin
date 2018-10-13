package kaeuchoa.alura_kotlin_pt1.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaMoedaBR
import kaeuchoa.alura_kotlin_pt1.models.Resumo
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualizaView(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val receitaTotal = resumo.receita
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = receitaTotal.formataParaMoedaBR()
        }
    }

    private fun adicionaDespesa() {
        val despesaTotal = resumo.despesa
        with(view.resumo_card_despesa){
            setTextColor(corDespesa)
            text = despesaTotal.formataParaMoedaBR()
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.formataParaMoedaBR()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) {
           return corReceita
        }
        return corDespesa

    }

}