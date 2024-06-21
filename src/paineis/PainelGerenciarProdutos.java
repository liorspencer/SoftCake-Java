package paineis;

import classes.IdBotao;
import classes.Produto;
import net.miginfocom.swing.MigLayout;
import telas.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PainelGerenciarProdutos extends JPanel {
    private List<Produto> produtos;
    private JPanel jpGerenciaPedido,jpContainerInterno;
    private JScrollPane jspPainelGerenciaPedido;
    private JLabel jlNomeProdutoSelecao,jlIDProduto,jlIDProdutoResultado,jlNomeProdutoSelecaoResultado, jlDescricaoSelecao, jlDescricaoSelecaoResultado,jlPesoSelecao,jlPesoSelecaoResultado,jlPrecoSelecao,jlPrecoSelecaoResultado;
    private ArrayList<IdBotao> jbAdicionar = new ArrayList<>();
    public PainelGerenciarProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {

    }

    private void criarEventos() {
        //painel escolha produto
        jpGerenciaPedido = new JPanel();
        jpGerenciaPedido.setLayout(new GridLayout(0, 1, 10, 10));
        for (Produto produto : produtos) {//foreach para circular entre todas as opções dentro do array pedidos
            //CRIAR COMPONENTES
            jpContainerInterno = new JPanel(new MigLayout("fillx", "5[grow 0]5[fill,grow 100]5"));
            jpContainerInterno.setBackground(Color.WHITE);
            jlIDProduto = new JLabel("ID:");
            jlIDProdutoResultado = new JLabel(String.valueOf(produto.getId()));
            jlNomeProdutoSelecao = new JLabel("Produto:");
            jlNomeProdutoSelecaoResultado = new JLabel(produto.getNome());
            jlDescricaoSelecao = new JLabel("Descrição:");
            jlDescricaoSelecaoResultado = new JLabel(produto.getDescricao());
            jlPesoSelecao = new JLabel("Peso (g):");
            jlPesoSelecaoResultado = new JLabel(String.valueOf(produto.getPeso()));
            jlPrecoSelecao = new JLabel("Preço (un):");
            jlPrecoSelecaoResultado = new JLabel(String.valueOf(produto.getPreco()));
            if (produto.isDisponibilidade()){
                jbAdicionar.add(new IdBotao("Indisponibilizar", produto.getId(),0));//Adicionar botão no array de botões que guardem o id do pedido.
            } else {
                jbAdicionar.add(new IdBotao("Disponibilizar", produto.getId(),1));//Adicionar botão no array de botões que guardem o id do pedido.
            }
            //ADICIONAR COMPONENTES NO PAINEL
            jpContainerInterno.add(jlIDProduto);
            jpContainerInterno.add(jlIDProdutoResultado, "gapy 5 0,wrap");
            jpContainerInterno.add(jlNomeProdutoSelecao);
            jpContainerInterno.add(jlNomeProdutoSelecaoResultado, "wrap");
            jpContainerInterno.add(jlDescricaoSelecao);
            jpContainerInterno.add(jlDescricaoSelecaoResultado, "wrap");
            jpContainerInterno.add(jlPesoSelecao);
            jpContainerInterno.add(jlPesoSelecaoResultado, "wrap");
            jpContainerInterno.add(jlPrecoSelecao);
            jpContainerInterno.add(jlPrecoSelecaoResultado);
            for (IdBotao idBotao : jbAdicionar) {
                if (idBotao.getId() == produto.getId() && idBotao.getOpcao() == 0) {
                    idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            produtos.get((idBotao.getId()-1)).setDisponibilidade(false);
                            JOptionPane.showMessageDialog(null,"Status do produto ("+idBotao.getId()+") atualizado para: Indisponível","Atualização",JOptionPane.PLAIN_MESSAGE);
                            TelaPrincipal.reiniciarPainelGerenciarProdutos();                        }
                    });
                    jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                }else if (idBotao.getId() == produto.getId() && idBotao.getOpcao() == 1) {
                    idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            produtos.get((idBotao.getId()-1)).setDisponibilidade(true);
                            JOptionPane.showMessageDialog(null,"Status do produto ("+idBotao.getId()+") atualizado para: Disponível","Atualização",JOptionPane.PLAIN_MESSAGE);
                            TelaPrincipal.reiniciarPainelGerenciarProdutos();
                        }
                    });
                    jpContainerInterno.add(idBotao, "gapy 0 5,dock south");
                }
            }
            jpGerenciaPedido.add(jpContainerInterno);
            jpGerenciaPedido.setBackground(Color.darkGray);
        }
        jspPainelGerenciaPedido = new JScrollPane(jpGerenciaPedido, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jspPainelGerenciaPedido);
        jspPainelGerenciaPedido.setBounds(20, 20, 445, 400);

    }
}
