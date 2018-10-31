package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.delegate.TransacaoDelegate
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kaeuchoa.alura_kotlin_pt1.ui.ResumoView
import kaeuchoa.alura_kotlin_pt1.ui.adapters.ListaTransacoesAdapter
import kaeuchoa.alura_kotlin_pt1.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val listaTransacoes : MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()

        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val decorView: View = window.decorView
        val resumoView = ResumoView(this,decorView, listaTransacoes)
        resumoView.atualizaView()

        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                    .configuraDialog(object : TransacaoDelegate {
                        override fun delegate(transacaoCriada: Transacao) {
                            atualizaTransacoes(transacaoCriada)
                            lista_transacoes_adiciona_menu.close(true)
                        }
                    })
        }
    }

    private fun atualizaTransacoes(novaTransacao: Transacao) {
        listaTransacoes.add(novaTransacao)
        configuraResumo()
        configuraListaTransacoes()
    }


    private fun configuraListaTransacoes() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }



}