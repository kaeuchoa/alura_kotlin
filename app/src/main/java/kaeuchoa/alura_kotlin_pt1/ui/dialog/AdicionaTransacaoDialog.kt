package kaeuchoa.alura_kotlin_pt1.ui.dialog

import android.content.Context
import android.view.ViewGroup
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao

class AdicionaTransacaoDialog(viewGroup: ViewGroup,
                              context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String = context.getString(R.string.btn_positive_adiciona)

    override fun tituloPor(tipo: TipoTransacao): Int {
        if (tipo == TipoTransacao.RECEITA)
            return R.string.adiciona_receita

        return R.string.adiciona_despesa
    }
}