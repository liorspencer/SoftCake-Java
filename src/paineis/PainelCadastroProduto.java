package paineis;

import classes.FontesPadrao;
import classes.LimitarCaracteres;
import classes.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelCadastroProduto extends JPanel {
    private FontesPadrao fontes;
    private JLabel jlProduto, jlNome, jlDescricao, jlPreco, jlPeso, jlDisponibilidade;
    private JScrollPane jspDescricao;
    private JTextField jtfNome,jtfPreco, jtfPeso;
    private JTextArea jtaDescricao;
    private JRadioButton jrbSim, jrbNao;
    private JButton jbCadastrar;
    private ButtonGroup bgDisponibilidade;
    private List<Produto> produto;


    //construtor

    public PainelCadastroProduto(List<Produto> produto) {
        super();
        this.produto = produto;
        setSize(500, 400);
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        //objetos
        fontes = new FontesPadrao();
        jlProduto = new JLabel("Cadastrar Produto");
        jlNome = new JLabel("Nome:");
        jlDescricao = new JLabel("Descrição:");
        jlPreco = new JLabel("Preço:");
        jlPeso = new JLabel("Peso (g):");
        jlDisponibilidade = new JLabel("Disponível:");
        jtfNome = new JTextField();
        jtfNome.setDocument(new LimitarCaracteres(50,LimitarCaracteres.TipoDadoEntrada.LETRAS));
        jtfPreco = new JTextField();
        jtfPreco.setDocument(new LimitarCaracteres(13,LimitarCaracteres.TipoDadoEntrada.DECIMAL));
        jtfPeso = new JTextField();
        jtfPeso.setDocument(new LimitarCaracteres(15,LimitarCaracteres.TipoDadoEntrada.DECIMAL));
        jtaDescricao = new JTextArea();
        jspDescricao = new JScrollPane(jtaDescricao,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jrbSim = new JRadioButton("Sim", true);
        jrbNao = new JRadioButton("Nao");
        jrbSim.setOpaque(false);
        jrbNao.setOpaque(false);
        jbCadastrar = new JButton("Cadastrar");
        bgDisponibilidade = new ButtonGroup();

        //adicionando na tela
        add(jlProduto);
        add(jlNome);
        add(jlDescricao);
        add(jlPreco);
        add(jlPeso);
        add(jlDisponibilidade);
        add(jtfNome);
        add(jtfPreco);
        add(jtfPeso);
        add(jspDescricao);
        add(jrbSim);
        add(jrbNao);
        add(jbCadastrar);
        bgDisponibilidade.add(jrbSim);
        bgDisponibilidade.add(jrbNao);

        //estiizar componentes
        jlProduto.setHorizontalAlignment(SwingConstants.CENTER);
        jlProduto.setFont(fontes.fontArialTitulo());
        jlNome.setFont(fontes.fontArialCorpo());
        jtfNome.setFont(fontes.fontArialCorpo());
        jlPreco.setFont(fontes.fontArialCorpo());
        jtfPreco.setFont(fontes.fontArialCorpo());
        jlDescricao.setFont(fontes.fontArialCorpo());
        jspDescricao.setFont(fontes.fontArialCorpo());
        jtaDescricao.setFont(fontes.fontArialCorpo());
        jtfPeso.setFont(fontes.fontArialCorpo());
        jlPeso.setFont(fontes.fontArialCorpo());
        jlDisponibilidade.setFont(fontes.fontArialCorpo());
        jrbSim.setFont(fontes.fontArialCorpo());
        jrbNao.setFont(fontes.fontArialCorpo());
        jbCadastrar.setFont(fontes.fontArialBotao());
        //dimensionar componentes
        jlProduto.setBounds(0, 10, 485, 20);
        jlNome.setBounds(20, 40, 50, 20);
        jtfNome.setBounds(100, 35, 360, 30);
        jlPreco.setBounds(20, 80, 50, 20);
        jtfPreco.setBounds(100, 75, 360, 30);
        jlDescricao.setBounds(20, 120, 80, 20);
        jspDescricao.setBounds(100,120,360,160);
        jtaDescricao.setBounds(20,120,360,160);
        jtfPeso.setBounds(100, 300, 360, 30);
        jlPeso.setBounds(20, 300, 70, 20);
        jlDisponibilidade.setBounds(20, 340, 70, 20);
        jrbSim.setBounds(100, 340, 100, 20);
        jrbNao.setBounds(200, 340, 100, 20);
        jbCadastrar.setBounds(195, 380, 100, 30);
    }

    private void criarEventos() {
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, descricao;
                int id;
                double preco,peso;
                boolean disponibilidade = true;
                String erro = "Erro: Preencha os seguintes campos corretamente:\n";
                if (!jtfNome.getText().isEmpty() && !jtaDescricao.getText().isEmpty() && !jtfPreco.getText().isEmpty() && !jtfPeso.getText().isEmpty()) {
                    try {
                        nome = jtfNome.getText();
                        if (jrbSim.isSelected()) {
                            disponibilidade = true;
                        } else if (jrbNao.isSelected()) {
                            disponibilidade = false;
                        }
                        id = produto.size()+1;
                        descricao = jtaDescricao.getText();
                        preco = Double.parseDouble(jtfPreco.getText().replace(',','.'));
                        peso = Double.parseDouble(jtfPeso.getText().replace(',','.'));
                        produto.add(new Produto(id,nome,descricao,preco,peso,disponibilidade));
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        jtfNome.setText("");
                        jtaDescricao.setText("");
                        jtfPreco.setText("");
                        jtfPeso.setText("");
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Erro: Preencha os seguintes campos apenas\n com números reais:\n\nPreço\nPeso", "ERRO", JOptionPane.ERROR_MESSAGE);
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
                    if (jtfPeso.getText().isEmpty()) {
                        erro += "\nPeso";
                    }
                    JOptionPane.showMessageDialog(null, erro, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
