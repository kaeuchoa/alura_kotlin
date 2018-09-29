package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kaeuchoa.alura_kotlin_pt1.ui.adapters.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(
                Transacao(
                        valor = BigDecimal(20.50),
                        tipo = TipoTransacao.DESPESA),
                Transacao(
                        valor = BigDecimal(100),
                        categoria = "Economia",
                        tipo = TipoTransacao.RECEITA),
                Transacao(
                        valor = BigDecimal(50),
                        tipo = TipoTransacao.DESPESA,
                        categoria = "Compras"),
                Transacao(
                        valor = BigDecimal(150.0),
                        tipo = TipoTransacao.RECEITA,
                        categoria = "Bônus"),
                Transacao(
                        valor = BigDecimal(25.35),
                        tipo = TipoTransacao.DESPESA,
                        categoria = "Teste de Categoria com nome grande"
                )
        )

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)


    }

}