package kaeuchoa.alura_kotlin_pt1.ui.dialog

import android.content.Context
import android.view.ViewGroup
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.formataParaBrasileiro
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao

class AlteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context) : FormularioTransacaoDialog(context, viewGroup){
    override val tituloBotaoPositivo: String = context.getString(R.string.btn_positive_altera)

    override fun tituloPor(tipo: TipoTransacao): Int {
        if(tipo == TipoTransacao.RECEITA)
            return R.string.altera_receita

        return R.string.altera_despesa
    }

    fun abreDialog(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {
        val tipo: TipoTransacao = transacao.tipo
        super.abreDialog(tipo, delegate)
        inicializaCampos(transacao, tipo)

    }

    private fun inicializaCampos(transacao: Transacao, tipo: TipoTransacao) {
        inicializaCampoData(transacao)
        inicializaCampoValor(transacao)
        inicializaCampoCategoria(tipo, transacao)
    }

    private fun inicializaCampoCategoria(tipo: TipoTransacao, transacao: Transacao) {
        val categoriasArray = context.resources.getStringArray(categoriaPor(tipo))
        val posicaoCategoria = categoriasArray.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }
}