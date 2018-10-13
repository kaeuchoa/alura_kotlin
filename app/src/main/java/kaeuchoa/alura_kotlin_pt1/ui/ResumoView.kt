package kaeuchoa.alura_kotlin_pt1.ui

import android.view.View
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaMoedaBR
import kaeuchoa.alura_kotlin_pt1.models.Resumo
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView (private val view : View,
                  transacoes: List<Transacao>){

    private val resumo = Resumo(transacoes)

     fun adicionaReceita() {
        val receitaTotal = resumo.valorReceita()
        view.resumo_card_receita.text = receitaTotal.formataParaMoedaBR()
    }

    fun adicionaDespesa() {
        val despesaTotal = resumo.valorDespesa()
        view.resumo_card_despesa.text = despesaTotal.formataParaMoedaBR()
    }

    fun adicionaTotal(){
        val total = resumo.calculaTotal()
        view.resumo_card_total.text = total.formataParaMoedaBR()
    }

}