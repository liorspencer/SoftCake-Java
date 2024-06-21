package paineis;

import classes.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PainelCadastroPedido extends JPanel {
    private FontesPadrao fontes;
    private MaskFormatter mfCpf;
    private JLabel jlPedido, jlNome, jlQuantidade, jlDescricao, jlCpf, jlPreco, jlNomeProdutoSelecao, jlNomeProdutoSelecaoResultado, jlDescricaoSelecao, jlDescricaoSelecaoResultado, jlPesoSelecao, jlPesoSelecaoResultado, jlPrecoSelecao, jlPrecoSelecaoResultado;
    private JScrollPane jspDescricao, jspPainelSelecaoProduto;
    private int itens = 1;
    private boolean mostrandoProdutos = false;
    private JPanel jpEscolhaProduto, jpContainerInterno;
    private JTextField jtfNome, jtfQuantidade, jtfPreco;
    private JFormattedTextField jtfCpf;
    private JTextArea jtaDescricao;
    private JSeparator jsSelecao;
    private ArrayList<IdBotao> jbAdicionar = new ArrayList<>();
    private JButton jbAdicionarProdutos, jbCadastrar;
    private JFrame tela;
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
        try {
            mfCpf = new MaskFormatter("###.###.###-##");
            mfCpf.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        fontes = new FontesPadrao();
        jlPedido = new JLabel("Cadastrar Pedido");
        jlNome = new JLabel("Nome:");
        jlQuantidade = new JLabel("Quantidade:");
        jlDescricao = new JLabel("Descrição:");
        jlCpf = new JLabel("CPF:");
        jlPreco = new JLabel("Preço:");
        jtfNome = new JTextField();
        jtfNome.setDocument(new LimitarCaracteres(50,LimitarCaracteres.TipoDadoEntrada.LETRAS));
        jtfCpf = new JFormattedTextField(mfCpf);
        jtfPreco = new JTextField();
        jtfPreco.setDocument(new LimitarCaracteres(13,LimitarCaracteres.TipoDadoEntrada.DECIMAL));
        jtaDescricao = new JTextArea();
        jspDescricao = new JScrollPane(jtaDescricao,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jbAdicionarProdutos = new JButton("Adicionar Produtos");
        jtfQuantidade = new JTextField();
        jtfQuantidade.setDocument(new LimitarCaracteres(2,LimitarCaracteres.TipoDadoEntrada.NUMERO_INTEIRO));
        jbCadastrar = new JButton("Cadastrar pedido");
        jsSelecao = new JSeparator(SwingConstants.VERTICAL);
        jsSelecao.setVisible(false);

        //adicionando na tela
        add(jlPedido);
        add(jlNome);
        add(jlQuantidade);
        add(jlDescricao);
        add(jlCpf);
        add(jlPreco);
        add(jtfNome);
        add(jtfCpf);
        add(jtfPreco);
        add(jbAdicionarProdutos);
        add(jtfQuantidade);
        add(jspDescricao);
        add(jsSelecao);
        add(jbCadastrar);

        //estilizar componentes
        jlPedido.setFont(fontes.fontArialTitulo());
        jlPedido.setHorizontalAlignment(SwingConstants.CENTER);
        jlNome.setFont(fontes.fontArialCorpo());
        jtfNome.setFont(fontes.fontArialCorpo());
        jlCpf.setFont(fontes.fontArialCorpo());
        jtfCpf.setFont(fontes.fontArialCorpo());
        jbAdicionarProdutos.setFont(fontes.fontArialBotao());
        jlQuantidade.setFont(fontes.fontArialCorpo());
        jtfQuantidade.setFont(fontes.fontArialCorpo());
        jtfQuantidade.setText("1");
        jlDescricao.setFont(fontes.fontArialCorpo());
        jspDescricao.setFont(fontes.fontArialCorpo());
        jtaDescricao.setEditable(false);
        jtaDescricao.setFont(fontes.fontArialCorpo());
        jtfPreco.setFont(fontes.fontArialCorpo());
        jtfPreco.setEditable(false);
        jtfPreco.setText("0");
        jlPreco.setFont(fontes.fontArialCorpo());
        jbCadastrar.setFont(fontes.fontArialBotao());


        //dimensionar componentes
        jlPedido.setBounds(0, 10, 485, 20);
        jlNome.setBounds(20, 40, 50, 20);
        jtfNome.setBounds(100, 35, 360, 30);
        jlCpf.setBounds(20, 80, 50, 20);
        jtfCpf.setBounds(100, 75, 360, 30);
        jbAdicionarProdutos.setBounds(100,115,200,30);
        jlQuantidade.setBounds(320,120,80,20);
        jtfQuantidade.setBounds(400,115,40,30);
        jlDescricao.setBounds(20, 160, 80, 20);
        jspDescricao.setBounds(100,160,360,150);
        jtfPreco.setBounds(100, 330, 360, 30);
        jlPreco.setBounds(20, 330, 70, 20);
        jbCadastrar.setBounds(150, 400, 200, 30);
        jsSelecao.setBounds(480, 0, 2, 500);

        //painel escolha produto
        jpEscolhaProduto = new JPanel();
        jpEscolhaProduto.setLayout(new GridLayout(0, 1, 10, 10));
        for (Produto produto : produtos) {//foreach para circular entre todas as opções dentro do array carros
            if (produto.isDisponibilidade()) {//checar se o carro está disponível
                //CRIAR COMPONENTES
                jpContainerInterno = new JPanel(new MigLayout("fillx", "5[grow 0]5[fill,grow 100]5"));
                jpContainerInterno.setBackground(Color.WHITE);
                jlNomeProdutoSelecao = new JLabel("Produto:");
                jlNomeProdutoSelecaoResultado = new JLabel(produto.getNome());
                jlDescricaoSelecao = new JLabel("Descrição:");
                jlDescricaoSelecaoResultado = new JLabel(produto.getDescricao());
                jlPesoSelecao = new JLabel("Peso (g):");
                jlPesoSelecaoResultado = new JLabel(String.valueOf(produto.getPeso()));
                jlPrecoSelecao = new JLabel("Preço (un):");
                jlPrecoSelecaoResultado = new JLabel(String.valueOf(produto.getPreco()));
                jbAdicionar.add(new IdBotao("Adicionar", produto.getId()));//Adicionar botão no array de botões que guardem o id do carro.
                //ADICIONAR COMPONENTES NO PAINEL
                jpContainerInterno.add(jlNomeProdutoSelecao);
                jpContainerInterno.add(jlNomeProdutoSelecaoResultado, "gapy 5 0,wrap");
                jpContainerInterno.add(jlDescricaoSelecao);
                jpContainerInterno.add(jlDescricaoSelecaoResultado, "wrap");
                jpContainerInterno.add(jlPesoSelecao);
                jpContainerInterno.add(jlPesoSelecaoResultado, "wrap");
                jpContainerInterno.add(jlPrecoSelecao);
                jpContainerInterno.add(jlPrecoSelecaoResultado);
                for (IdBotao idBotao : jbAdicionar) {
                    if (idBotao.getId() == produto.getId()) {
                        idBotao.addActionListener(new ActionListener() {//ADICIONAR EVENTO AO BOTÃO GERADO.
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Double precoAtual = Double.parseDouble(jtfPreco.getText());
                                precoAtual += (produtos.get((idBotao.getId()-1)).getPreco()*Integer.parseInt(jtfQuantidade.getText()));
                                jtfPreco.setText(String.valueOf(precoAtual));
                                jtaDescricao.append(itens+". "+jtfQuantidade.getText()+"x "+produtos.get((idBotao.getId()-1)).getNome()+"\nValor Unitário: "+produtos.get((idBotao.getId()-1)).getPreco()+"\n\n");
                                itens++;
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
        jspPainelSelecaoProduto.setBounds(495, 20, 370, 400);
        jspPainelSelecaoProduto.setVisible(false);
    }

    private void criarEventos() {
        jbAdicionarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tela = (JFrame) getParent().getParent().getParent().getParent();
                if (mostrandoProdutos) {
                    jspPainelSelecaoProduto.setVisible(false);
                    jsSelecao.setVisible(false);
                    mostrandoProdutos = false;
                    jbAdicionarProdutos.setText("Adicionar Produtos");
                    tela.setSize(500, 500);
                } else {
                    jspPainelSelecaoProduto.setVisible(true);
                    jsSelecao.setVisible(true);
                    mostrandoProdutos = true;
                    jbAdicionarProdutos.setText("Ocultar");
                    tela.setSize(900, 500);
                }

            }
        });
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
                        JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
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
                    if (jtfPreco.getText().isEmpty()) {
                        erro += "\nPreço";
                    }
                    JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
