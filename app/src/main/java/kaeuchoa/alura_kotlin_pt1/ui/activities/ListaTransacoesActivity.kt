package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.delegate.TransacaoDelegate
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kaeuchoa.alura_kotlin_pt1.ui.ResumoView
import kaeuchoa.alura_kotlin_pt1.ui.adapters.ListaTransacoesAdapter
import kaeuchoa.alura_kotlin_pt1.ui.dialog.AdicionaTransacaoDialog
import kaeuchoa.alura_kotlin_pt1.ui.dialog.AlteraTransacaoDialog
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
            abirDialogAdicao(TipoTransacao.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            abirDialogAdicao(TipoTransacao.DESPESA)
        }
    }

    private fun abirDialogAdicao(tipo: TipoTransacao) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                .configuraDialog(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        listaTransacoes.add(transacao)
                        atualizaTransacoes()
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun abrirDialogAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(window.decorView as ViewGroup, this)
                .configuraDialog(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        listaTransacoes[posicao] = transacao
                        atualizaTransacoes()
                    }
                })
    }

    private fun atualizaTransacoes() {
        configuraResumo()
        configuraListaTransacoes()
    }


    private fun configuraListaTransacoes() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
        lista_transacoes_listview.setOnItemClickListener { parent, view, position, id ->
            val transacao = listaTransacoes[position]
            abrirDialogAlteracao(transacao, position)
        }
    }



}