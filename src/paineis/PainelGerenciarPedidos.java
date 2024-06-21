package paineis;

import classes.IdBotao;
import classes.Pedido;
import net.miginfocom.swing.MigLayout;
import telas.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PainelGerenciarPedidos extends JPanel {
    private List<Pedido> pedidos;
    private JPanel jpEscolhaProduto,jpContainerInterno;
    private JScrollPane jspPainelSelecaoProduto;
    private JLabel jlNomeProdutoSelecao,jlNomeProdutoSelecaoResultado,jlDescricaoSelecao,jlDescricaoSelecaoResultado,jlPesoSelecao,jlPesoSelecaoResultado,jlPrecoSelecao,jlPrecoSelecaoResultado;
    private ArrayList<IdBotao> jbAdicionar = new ArrayList<>();
    public PainelGerenciarPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        //painel escolha produto
        jpEscolhaProduto = new JPanel();
        jpEscolhaProduto.setLayout(new GridLayout(0, 1, 10, 10));
        for (Pedido pedido : pedidos) {//foreach para circular entre todas as opções dentro do array pedidos
            if (pedido.isAtivo()) {//checar se o pedido está ativo
                //CRIAR COMPONENTES
                jpContainerInterno = new JPanel(new MigLayout("fillx", "5[grow 0]5[fill,grow 100]5","[][][fill,grow 100][][]"));
                jpContainerInterno.setBackground(Color.WHITE);
                jlNomeProdutoSelecao = new JLabel("ID do Pedido:");
                jlNomeProdutoSelecaoResultado = new JLabel(String.valueOf(pedido.getIdPedido()));
                jlDescricaoSelecao = new JLabel("Cliente:");
                jlDescricaoSelecaoResultado = new JLabel(pedido.getNomeCliente()+"-"+pedido.getIdCliente());
                jlPrecoSelecao = new JLabel("Preço (un):");
                jlPrecoSelecaoResultado = new JLabel(String.valueOf(pedido.getPrecoTotal()));
                jbAdicionar.add(new IdBotao("Concluir", pedido.getIdPedido(),0));//Adicionar botão no array de botões que guardem o id do pedido.
                jbAdicionar.add(new IdBotao("Cancelar", pedido.getIdPedido(),1));//Adicionar botão no array de botões que guardem o id do pedido.
                //ADICIONAR COMPONENTES NO PAINEL
                jpContainerInterno.add(jlNomeProdutoSelecao);
                jpContainerInterno.add(jlNomeProdutoSelecaoResultado, "gapy 5 0,wrap");
                jpContainerInterno.add(jlDescricaoSelecao);
                jpContainerInterno.add(jlDescricaoSelecaoResultado, "wrap");
                jpContainerInterno.add(jlPrecoSelecao);
                jpContainerInterno.add(jlPrecoSelecaoResultado);
                for (IdBotao idBotao : jbAdicionar) {
                    if (idBotao.getId() == pedido.getIdPedido() && idBotao.getOpcao() == 0) {
                        idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pedidos.get((idBotao.getId()-1)).setAtivo(false);
                                JOptionPane.showMessageDialog(null,"Status do pedido ("+idBotao.getId()+") atualizado para: Concluído","Atualização",JOptionPane.PLAIN_MESSAGE);
                                TelaPrincipal.reiniciarPainelGerenciarPedidos();
                            }
                        });
                        jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                    }
                    if (idBotao.getId() == pedido.getIdPedido() && idBotao.getOpcao() == 1) {
                        idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pedidos.get((idBotao.getId()-1)).setAtivo(false);
                                pedidos.get((idBotao.getId()-1)).setDescricao("--PEDIDO CANCELADO--\n"+pedidos.get((idBotao.getId()-1)).getDescricao()+"\n--PEDIDO CANCELADO--");
                                JOptionPane.showMessageDialog(null,"Status do pedido ("+idBotao.getId()+") atualizado para: Cancelado","Atualização",JOptionPane.PLAIN_MESSAGE);
                                TelaPrincipal.reiniciarPainelGerenciarPedidos();
                            }
                        });
                        jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                    }
                }
                jpEscolhaProduto.add(jpContainerInterno);
                jpEscolhaProduto.setBackground(Color.darkGray);
            }
        }
        jspPainelSelecaoProduto = new JScrollPane(jpEscolhaProduto, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jspPainelSelecaoProduto);
        jspPainelSelecaoProduto.setBounds(20, 20, 445, 400);
    }
}
