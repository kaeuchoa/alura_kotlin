package kaeuchoa.alura_kotlin_pt1.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kaeuchoa.alura_kotlin_pt1.R
import kaeuchoa.alura_kotlin_pt1.extensions.dao.TransacaoDAO
import kaeuchoa.alura_kotlin_pt1.models.TipoTransacao
import kaeuchoa.alura_kotlin_pt1.models.Transacao
import kaeuchoa.alura_kotlin_pt1.ui.ResumoView
import kaeuchoa.alura_kotlin_pt1.ui.adapters.ListaTransacoesAdapter
import kaeuchoa.alura_kotlin_pt1.ui.dialog.AdicionaTransacaoDialog
import kaeuchoa.alura_kotlin_pt1.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {
    private val transacaoDao = TransacaoDAO()
    private val listaTransacoes = transacaoDao.listaTransacoes
    private val menuID: Int = 200

    private val viewGroup by lazy {
        window.decorView as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraListaTransacoes()
    }

    private fun configuraResumo() {
        val decorView: View = window.decorView
        val resumoView = ResumoView(this, decorView, listaTransacoes)
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
                .abreDialog(tipo, delegate = { transacaoCriada ->
                    adiciona(transacaoCriada)
                    atualizaTransacoes()
                    lista_transacoes_adiciona_menu.close(true)
                })
    }

    private fun adiciona(transacao: Transacao) {
        transacaoDao.adiciona(transacao)
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        transacaoDao.altera(transacao,posicao)
    }

    private fun removeTransacao(posicao: Int) {
        transacaoDao.remove(posicao)
        atualizaTransacoes()
    }

    private fun abrirDialogAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewGroup, this)
                .abreDialog(transacao, delegate = { transacaoAlterada ->
                    altera(transacaoAlterada, posicao)
                    atualizaTransacoes()
                })
    }


    private fun atualizaTransacoes() {
        configuraResumo()
        configuraListaTransacoes()
    }

    private fun configuraListaTransacoes() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(listaTransacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = listaTransacoes[posicao]
                abrirDialogAlteracao(transacao, posicao)
            }

            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, menuID, Menu.NONE, getString(R.string.context_menu_title))
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuId = item?.itemId
        when (menuId) {
            this.menuID -> {
                val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val posicao = menuInfo.position
                removeTransacao(posicao)
                Snackbar.make(rlMainLayout, getString(R.string.msg_transacao_removida), Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onContextItemSelected(item)
    }


}