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

public class PainelPesquisa extends JPanel {
    private JLabel jlPesquisa;
    private JButton jbClientes, jbProdutos, jbPedidos;
    private FontesPadrao fontes;
    private JComboBox jcbClientes, jcbProdutos, jcbPedidos;
    private JTextField jtfPesquisa;
    private String[] jcbClientesItens = {"ID","E-mail","Telefone","CPF"},jcbProdutosItens = {"ID","Nome","Preço","Peso"},jcbPedidosItens = {"ID do Pedido","ID do Cliente"};
    private JTextArea jtaMostrar;
    private JScrollPane jsMostrar;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Pedido> pedidos;

    public PainelPesquisa(List<Cliente> clientes,List<Produto> produtos,List<Pedido> pedidos) {
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
        jlPesquisa = new JLabel("Pesquisa");
        jbClientes = new JButton("Clientes");
        jbProdutos = new JButton("Produtos");
        jbPedidos = new JButton("Pedidos");
        jcbClientes = new JComboBox(jcbClientesItens);
        jcbProdutos = new JComboBox(jcbProdutosItens);
        jcbPedidos = new JComboBox(jcbPedidosItens);
        jtfPesquisa = new JTextField();
        jtaMostrar = new JTextArea();
        jsMostrar = new JScrollPane(jtaMostrar);

        //adicionar
        add(jlPesquisa);
        add(jbClientes);
        add(jbProdutos);
        add(jbPedidos);
        add(jcbClientes);
        add(jcbProdutos);
        add(jcbPedidos);
        add(jtfPesquisa);
        add(jsMostrar);

        //estilizar
        jlPesquisa.setFont(fontes.fontArialCorpo());
        jtfPesquisa.setFont(fontes.fontArialCorpo());
        jbClientes.setFont(fontes.fontArialBotao());
        jbProdutos.setFont(fontes.fontArialBotao());
        jbPedidos.setFont(fontes.fontArialBotao());
        jcbClientes.setFont(fontes.fontArialCorpo());
        jcbProdutos.setFont(fontes.fontArialCorpo());
        jcbPedidos.setFont(fontes.fontArialCorpo());
        jsMostrar.setFont(fontes.fontArialCorpo());

        //dimensionar
        jlPesquisa.setBounds(215,5,95,20);
        jtfPesquisa.setBounds(165,25,155,30);
        jbClientes.setBounds(20, 85, 95, 30);
        jbProdutos.setBounds(195, 85, 95, 30);
        jbPedidos.setBounds(370, 85, 95, 30);
        jcbClientes.setBounds(20,55,95,30);
        jcbProdutos.setBounds(195,55,95,30);
        jcbPedidos.setBounds(370,55,95,30);
        jsMostrar.setBounds(20, 120, 440, 300);

    }
    private void criarEventos() {
        jbClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = jcbClientes.getSelectedIndex();
                jtaMostrar.setText("Clientes Cadastrados");
                if (opcao==0){
                    for (Cliente cliente: clientes){
                        if (cliente.getId()==Integer.parseInt(jtfPesquisa.getText())){
                            jtaMostrar.append(cliente.mostrarCadastro());
                        }
                    }
                } else if(opcao==1){
                    for (Cliente cliente: clientes){
                        if (cliente.getEmail().equals(jtfPesquisa.getText())){
                            jtaMostrar.append(cliente.mostrarCadastro());
                        }
                    }
                } else if(opcao==2){
                    for (Cliente cliente: clientes){
                        if (cliente.getTelefone().equals(jtfPesquisa.getText())){
                            jtaMostrar.append(cliente.mostrarCadastro());
                        }
                    }
                } else if(opcao==3){
                    for (Cliente cliente: clientes){
                        if (cliente.getCpf().equals(jtfPesquisa.getText())){
                            jtaMostrar.append(cliente.mostrarCadastro());
                        }
                    }
                }
            }
        });
        jbProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = jcbProdutos.getSelectedIndex();
                jtaMostrar.setText("Produtos Cadastrados");
                if (opcao==0){
                    for (Produto produto: produtos){
                        if (produto.getId()==Integer.parseInt(jtfPesquisa.getText())){
                            jtaMostrar.append(produto.mostrarCadastro());
                        }
                    }
                } else if(opcao==1){
                    for (Produto produto: produtos){
                        if (produto.getNome().equals(jtfPesquisa.getText())){
                            jtaMostrar.append(produto.mostrarCadastro());
                        }
                    }
                } else if(opcao==2){
                    for (Produto produto: produtos){
                        if (produto.getPreco()==Double.parseDouble(jtfPesquisa.getText())){
                            jtaMostrar.append(produto.mostrarCadastro());
                        }
                    }
                } else if(opcao==3){
                    for (Produto produto: produtos){
                        if (produto.getPeso()==Double.parseDouble(jtfPesquisa.getText())){
                            jtaMostrar.append(produto.mostrarCadastro());
                        }
                    }
                }
            }
        });
        jbPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = jcbProdutos.getSelectedIndex();
                jtaMostrar.setText("Pedidos Cadastrados");
                if (opcao==0){
                    for (Pedido pedido: pedidos){
                        if (pedido.getIdPedido()==Integer.parseInt(jtfPesquisa.getText())){
                            jtaMostrar.append(pedido.mostrarCadastro());
                        }
                    }
                } else if(opcao==1){
                    for (Pedido pedido: pedidos){
                        if (pedido.getIdCliente()==Integer.parseInt(jtfPesquisa.getText())){
                            jtaMostrar.append(pedido.mostrarCadastro());
                        }
                    }
                }
            }
        });
    }
}
