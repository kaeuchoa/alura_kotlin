package kaeuchoa.alura_kotlin_pt1.ui.dialog

import android.content.Context
import android.view.ViewGroup
import kaeuchoa.alura_kotlin_pt1.delegate.TransacaoDelegate
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao

class AlteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context) : FormularioTransacaoDialog(context, viewGroup){

    fun abreDialog(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val tipo: TipoTransacao = transacao.tipo
        super.abreDialog(tipo, transacaoDelegate)

        campoData.setText(transacao.data.formataParaBrasileiro())
        campoValor.setText(transacao.valor.toString())
        val categoriasArray = context.resources.getStringArray(categoriaPor(tipo))
        val posicaoCategoria = categoriasArray.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria,true)

    }
}