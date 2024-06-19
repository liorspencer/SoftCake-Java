package paineis;

import classes.Cliente;
import classes.LimitarCaracteres;
import classes.Pedido;
import classes.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelCadastroPedido extends JPanel {
    private JLabel jlPedido, jlNome, jlDescricao, jlCpf, jlPreco;
    private JScrollPane jspDescricao;
    private JTextField jtfNome, jtfCpf, jtfPreco;
    private JTextArea jtaDescricao;
    private JButton jbCadastrar;
    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private List<Produto> produtos;


    //construtor

    public PainelCadastroPedido(List<Pedido> pedidos, List<Cliente> clientes, List<Produto> produtos) {
        super();
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
        setSize(500, 400);
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        //objetos
        jlPedido = new JLabel("Cadastrar Pedido");
        jlNome = new JLabel("Nome:");
        jlDescricao = new JLabel("Descrição:");
        jlCpf = new JLabel("CPF:");
        jlPreco = new JLabel("Preço:");
        jtfNome = new JTextField();
        jtfNome.setDocument(new LimitarCaracteres(50,LimitarCaracteres.TipoDadoEntrada.letras));
        jtfCpf = new JTextField();
        jtfCpf.setDocument(new LimitarCaracteres(11,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jtfPreco = new JTextField();
        jtfPreco.setDocument(new LimitarCaracteres(13,LimitarCaracteres.TipoDadoEntrada.decimal));
        jtaDescricao = new JTextArea();
        jspDescricao = new JScrollPane(jtaDescricao,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jbCadastrar = new JButton("Cadastrar preço");

        //adicionando na tela
        add(jlPedido);
        add(jlNome);
        add(jlDescricao);
        add(jlCpf);
        add(jlPreco);
        add(jtfNome);
        add(jtfCpf);
        add(jtfPreco);
        add(jspDescricao);
        add(jbCadastrar);

        //dimensionar componentes
        jlPedido.setBounds(190, 10, 120, 20);
        jlNome.setBounds(20, 40, 50, 20);
        jtfNome.setBounds(100, 40, 360, 20);
        jlCpf.setBounds(20, 80, 50, 20);
        jtfCpf.setBounds(100, 80, 360, 20);
        jlDescricao.setBounds(20, 120, 80, 20);
        jspDescricao.setBounds(100,120,360,80);
        jtaDescricao.setBounds(20,140,360,80);
        jtfPreco.setBounds(100, 220, 360, 20);
        jlPreco.setBounds(20, 220, 70, 20);
        jbCadastrar.setBounds(195, 300, 100, 20);
    }

    private void criarEventos() {
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, descricao;
                int idPedido,idCliente = 0;
                double preco;
                boolean disponibilidade = true;
                String erro = "Erro: Preencha os seguintes campos corretamente:\n";
                if (!jtfNome.getText().isEmpty() && !jtaDescricao.getText().isEmpty() && !jtfPreco.getText().isEmpty()) {
                    try {
                        nome = jtfNome.getText();
                        idPedido = pedidos.size()+1;
                        if (!jtfCpf.getText().isEmpty()){
                            for (Cliente cliente:clientes) {
                                if (cliente.getCpf().equals(jtfCpf.getText())){
                                    idCliente = cliente.getId();
                                    nome = cliente.getNome();
                                }
                            }
                        }
                        descricao = jtaDescricao.getText();
                        if (idCliente==0){
                            descricao+="\n\nCLIENTE NÃO CADASTRADO!";
                        }
                        preco = Double.parseDouble(jtfPreco.getText().replace(',','.'));
                        pedidos.add(new Pedido(nome,idPedido,idCliente,descricao,preco,disponibilidade));
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        jtfNome.setText("");
                        jtaDescricao.setText("");
                        jtfCpf.setText("");
                        jtfPreco.setText("");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Erro: Preencha os seguintes campos apenas\ncom números reais:\n\nPreço\n\n\nPreencha os seguintes campos apenas\ncom números inteiros:\n\nCPF", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (jtfNome.getText().isEmpty()) {
                        erro += "\nNome";
                    }
                    if (jtaDescricao.getText().isEmpty()) {
                        erro += "\nDescrição";
                    }
                    if (jtfCpf.getText().isEmpty()) {
                        erro += "\nCPF";
                    }
                    if (jtfPreco.getText().isEmpty()) {
                        erro += "\nPreço";
                    }
                    JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
