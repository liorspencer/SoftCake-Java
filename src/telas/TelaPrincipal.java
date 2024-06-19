package telas;

import arquivos.EscreverLerArquivos;
import classes.Cliente;
import classes.Pedido;
import classes.Produto;
import classes.Sobre;
import paineis.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame{
    private static Container contentPane;//usado para manipular uma tela
    private static JMenuBar jmbBarra;//usado para criar uma barra de menu
    private JMenu jmArquivo,jmCadastro,jmVisualizar,jmSobre;//usado para criar uma opção no menu
    private JMenuItem jmiSalvar,jmiCarregar,jmiSair,jmiCadastroCliente,jmiCadastroProduto,jmiCadastroPedidos,jmiDados,jmiPesquisa,jmiAplicativo,jmiAutores;
    private List<Cliente> clientesCarregados,clientes = new ArrayList<>();
    private List<Produto> produtosCarregados,produtos = new ArrayList<>();
    private List<Pedido> pedidosCarregados,pedidos = new ArrayList<>();
    private EscreverLerArquivos arquivos = new EscreverLerArquivos();
    private Sobre sobre = new Sobre();

    public TelaPrincipal(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,400);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);//centraliza a tela
        iniciarComponentes();
        criarEventos();
        clientesCarregados=arquivos.lerObjetoCliente();
        produtosCarregados=arquivos.lerObjetoProduto();
        pedidosCarregados=arquivos.lerObjetoPedido();
        try{
            clientes.addAll(clientesCarregados);
        }catch (NullPointerException ex){
            arquivos.escreverArquivoCliente(clientes);
        }
        try{
            produtos.addAll(produtosCarregados);
        }catch (NullPointerException ex){
            arquivos.escreverArquivoProduto(produtos);
        }
        try{
            pedidos.addAll(pedidosCarregados);
        }catch (NullPointerException ex){
            arquivos.escreverArquivoPedido(pedidos);
        }
        PainelLogin login = new PainelLogin();
        contentPane.add(login);
        contentPane.validate();
    }
    public static void desbloquearPrograma(){
        contentPane.removeAll();
        jmbBarra.setVisible(true);
    }
    private void iniciarComponentes() {

        //criar objetos
        contentPane = getContentPane();//devolve um objeto do tipo Container(tela)
        jmbBarra = new JMenuBar();
        setJMenuBar(jmbBarra);//adicionei a barra da tela
        jmbBarra.setVisible(false);

        //objetos da barra de menu
        jmArquivo = new JMenu("Arquivo");
        jmCadastro = new JMenu("Cadastro");
        jmVisualizar = new JMenu("Visualizar");
        jmSobre = new JMenu("Sobre");

        //Objetos de itens do menu
        //menu Arquivo
        jmiSalvar = new JMenuItem("Salvar");
        jmiCarregar = new JMenuItem("Carregar");
        jmiSair = new JMenuItem("Sair");

        //menu Cadastro
        jmiCadastroCliente = new JMenuItem("Cadastro de Clientes");
        jmiCadastroProduto = new JMenuItem("Cadastro de Produtos");
        jmiCadastroPedidos = new JMenuItem("Cadastro de Pedidos");

        //menu Visualizar
        jmiDados = new JMenuItem("Dados completos");
        jmiPesquisa = new JMenuItem("Pesquisar");

        //menu Aplicativo
        jmiAplicativo = new JMenuItem("Aplicativo");
        jmiAutores = new JMenuItem("Autores");

        //adicionando componentes

        //adicionando opções de menu na barra
        jmbBarra.add(jmArquivo);
        jmbBarra.add(jmCadastro);
        jmbBarra.add(jmVisualizar);
        jmbBarra.add(jmSobre);

        //adicionando itens nas opções de menu
        //opção Arquivo
        jmArquivo.add(jmiSalvar);
        jmArquivo.add(jmiCarregar);
        jmArquivo.add(jmiSair);

        //opção Cadastro
        jmCadastro.add(jmiCadastroCliente);
        jmCadastro.add(jmiCadastroProduto);
        jmCadastro.add(jmiCadastroPedidos);

        //opção Visualizar
        jmVisualizar.add(jmiDados);
        jmVisualizar.add(jmiPesquisa);

        //opção Sobre
        jmSobre.add(jmiAplicativo);
        jmSobre.add(jmiAutores);
    }

    private void criarEventos() {
        jmiSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arquivos.escreverArquivoCliente(clientes);
                arquivos.escreverArquivoProduto(produtos);
                arquivos.escreverArquivoPedido(pedidos);
                JOptionPane.showMessageDialog(null,"Dados salvos!");
            }
        });
        jmiCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientesCarregados=arquivos.lerObjetoCliente();
                produtosCarregados=arquivos.lerObjetoProduto();
                pedidosCarregados=arquivos.lerObjetoPedido();
                clientes.clear();
                clientes.addAll(clientesCarregados);
                produtos.clear();
                produtos.addAll(produtosCarregados);
                pedidos.clear();
                pedidos.addAll(pedidosCarregados);
                JOptionPane.showMessageDialog(null,"Dados carregados!");
            }
        });
        jmiCadastroCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelCadastroCliente cliente = new PainelCadastroCliente(clientes);//passar o endereço da matriz dinamica(ArrayList()<>) para o painel cadastroCliente
                contentPane.removeAll();//remove todos os componentes da tela
                contentPane.add(cliente);//adicionar o painel
                contentPane.validate();//validar os componentes
            }
        });
        jmiCadastroProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelCadastroProduto produto = new PainelCadastroProduto(produtos);
                contentPane.removeAll();//remove todos os componentes da tela
                contentPane.add(produto);//adicionar o painel
                contentPane.validate();//validar os componentes
            }
        });
        jmiCadastroPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelCadastroPedido pedido = new PainelCadastroPedido(pedidos,clientes,produtos);
                contentPane.removeAll();//remove todos os componentes da tela
                contentPane.add(pedido);//adicionar o painel
                contentPane.validate();//validar os componentes
            }
        });
        jmiDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelDados dados = new PainelDados(clientes,produtos,pedidos);
                contentPane.removeAll();//remove todos os componentes da tela
                contentPane.add(dados);//adicionar o painel
                contentPane.validate();//validar os componentes
            }
        });
        jmiPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelPesquisa pesquisa = new PainelPesquisa(clientes,produtos,pedidos);
                contentPane.removeAll();//remove todos os componentes da tela
                contentPane.add(pesquisa);//adicionar o painel
                contentPane.validate();//validar os componentes
            }
        });
        jmiAplicativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"SoftCake versão: "+ sobre.getVersao());
            }
        });
        jmiAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Autores:\n" + sobre.getAutores()+"\nAntigos Autores:\n"+sobre.getAutoresinicialprojeto());
            }
        });
    }
}
