package paineis;

import classes.Cliente;
import classes.FontesPadrao;
import classes.Pedido;
import classes.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelDados extends JPanel {
    private JButton jbClientes, jbProdutos, jbPedidos;
    private FontesPadrao fontes;
    private JCheckBox jckAtivo, jckInativo, jckDisponivel, jckIndisponivel;
    private JTextArea jtaMostrar;
    private JScrollPane jsMostrar;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Pedido> pedidos;

    public PainelDados(List<Cliente> clientes,List<Produto> produtos,List<Pedido> pedidos) {
        super();
        setSize(400, 400);
        setLayout(null);
        setBackground(Color.WHITE);
        this.clientes = clientes;
        this.produtos = produtos;
        this.pedidos = pedidos;
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        // Objetos
        fontes = new FontesPadrao();
        jbClientes = new JButton("Clientes");
        jbProdutos = new JButton("Produtos");
        jbPedidos = new JButton("Pedidos");
        jckInativo = new JCheckBox("Inativo");
        jckInativo.setOpaque(false);
        jckAtivo = new JCheckBox("Ativo");
        jckAtivo.setOpaque(false);
        jckDisponivel = new JCheckBox("Disponível");
        jckDisponivel.setOpaque(false);
        jckIndisponivel = new JCheckBox("Indisponível");
        jckIndisponivel.setOpaque(false);
        jtaMostrar =new JTextArea();
        jsMostrar = new JScrollPane(jtaMostrar);

        //adicionar
        add(jbClientes);
        add(jbProdutos);
        add(jbPedidos);
        add(jckInativo);
        add(jckDisponivel);
        add(jckIndisponivel);
        add(jckAtivo);
        add(jsMostrar);

        //estilizar
        jbClientes.setFont(fontes.fontArialBotao());
        jbProdutos.setFont(fontes.fontArialBotao());
        jbPedidos.setFont(fontes.fontArialBotao());
        jckAtivo.setFont(fontes.fontArialCorpo());
        jckInativo.setFont(fontes.fontArialCorpo());
        jckDisponivel.setFont(fontes.fontArialCorpo());
        jckIndisponivel.setFont(fontes.fontArialCorpo());
        jsMostrar.setFont(fontes.fontArialCorpo());

        //dimensionar
        jbClientes.setBounds(20, 15, 95, 30);
        jbProdutos.setBounds(195, 15, 95, 30);
        jbPedidos.setBounds(370, 15, 95, 30);
        jckAtivo.setBounds(370, 50, 100, 20);
        jckInativo.setBounds(370, 70, 100, 20);
        jckDisponivel.setBounds(195, 50, 100, 20);
        jckIndisponivel.setBounds(195,70,100,20);
        jsMostrar.setBounds(20, 120, 440, 300);

    }
    private void criarEventos() {
        jbClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaMostrar.setText("Clientes Cadastrados");
                for (Cliente cliente: clientes){
                    jtaMostrar.append(cliente.mostrarCadastro());
                }
            }
        });
        jbProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaMostrar.setText("Produtos Cadastrados");
                for (Produto produto: produtos){
                    if (jckDisponivel.isSelected() && produto.isDisponibilidade()) {
                        jtaMostrar.append(produto.mostrarCadastro());
                    } else if (jckIndisponivel.isSelected() && !produto.isDisponibilidade()) {
                        jtaMostrar.append(produto.mostrarCadastro());
                    }
                }
            }
        });
        jbPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jtaMostrar.setText("Pedidos Cadastrados");
                for (Pedido pedido: pedidos) {
                        if (jckInativo.isSelected() && !pedido.isAtivo()) {
                            jtaMostrar.append(pedido.mostrarCadastro());
                        } else if (jckAtivo.isSelected() && pedido.isAtivo()) {
                            jtaMostrar.append(pedido.mostrarCadastro());
                        }
                }
            }
        });
    }
}
