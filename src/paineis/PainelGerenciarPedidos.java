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
    private JPanel jpGerenciaPedido,jpContainerInterno;
    private JScrollPane jspPainelGerenciaPedido;
    private JLabel jlIDPedido, jlIDPedidoResultado, jlCliente, jlClienteResultado,jlPrecoSelecao,jlPrecoSelecaoResultado;
    private ArrayList<IdBotao> jbAdicionar = new ArrayList<>();
    public PainelGerenciarPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        //painel escolha produto
        jpGerenciaPedido = new JPanel();
        jpGerenciaPedido.setLayout(new GridLayout(0, 1, 10, 10));
        for (Pedido pedido : pedidos) {//foreach para circular entre todas as opções dentro do array pedidos
            if (pedido.isAtivo()) {//checar se o pedido está ativo
                //CRIAR COMPONENTES
                jpContainerInterno = new JPanel(new MigLayout("fillx", "5[grow 0]5[fill,grow 100]5"));
                jpContainerInterno.setBackground(Color.WHITE);
                jlIDPedido = new JLabel("ID do Pedido:");
                jlIDPedidoResultado = new JLabel(String.valueOf(pedido.getIdPedido()));
                jlCliente = new JLabel("Cliente:");
                jlClienteResultado = new JLabel(pedido.getNomeCliente()+"-"+pedido.getIdCliente());
                jlPrecoSelecao = new JLabel("Preço (un):");
                jlPrecoSelecaoResultado = new JLabel(String.valueOf(pedido.getPrecoTotal()));
                jbAdicionar.add(new IdBotao("Descrição", pedido.getIdPedido(),0));//Adicionar botão no array de botões que guardem o id do pedido.
                jbAdicionar.add(new IdBotao("Concluir", pedido.getIdPedido(),1));//Adicionar botão no array de botões que guardem o id do pedido.
                jbAdicionar.add(new IdBotao("Cancelar", pedido.getIdPedido(),2));//Adicionar botão no array de botões que guardem o id do pedido.
                //ADICIONAR COMPONENTES NO PAINEL
                jpContainerInterno.add(jlIDPedido);
                jpContainerInterno.add(jlIDPedidoResultado, "gapy 5 0,wrap");
                jpContainerInterno.add(jlCliente);
                jpContainerInterno.add(jlClienteResultado, "wrap");
                jpContainerInterno.add(jlPrecoSelecao);
                jpContainerInterno.add(jlPrecoSelecaoResultado);
                for (IdBotao idBotao : jbAdicionar) {
                    if (idBotao.getId() == pedido.getIdPedido() && idBotao.getOpcao() == 0) {
                        idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null,"Descrição:\n"+pedidos.get((idBotao.getId()-1)).getDescricao(),"Atualização",JOptionPane.PLAIN_MESSAGE);
                            }
                        });
                        jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                    }else if (idBotao.getId() == pedido.getIdPedido() && idBotao.getOpcao() == 1) {
                        idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pedidos.get((idBotao.getId()-1)).setAtivo(false);
                                JOptionPane.showMessageDialog(null,"Status do pedido ("+idBotao.getId()+") atualizado para: Concluído","Atualização",JOptionPane.PLAIN_MESSAGE);
                                TelaPrincipal.reiniciarPainelGerenciarPedidos();
                            }
                        });
                        jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                    }else if (idBotao.getId() == pedido.getIdPedido() && idBotao.getOpcao() == 2) {
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
                jpGerenciaPedido.add(jpContainerInterno);
                jpGerenciaPedido.setBackground(Color.darkGray);
            }
        }
        jspPainelGerenciaPedido = new JScrollPane(jpGerenciaPedido, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jspPainelGerenciaPedido);
        jspPainelGerenciaPedido.setBounds(20, 20, 445, 400);
    }
}
