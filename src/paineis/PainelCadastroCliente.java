package paineis;

import classes.Cliente;
import classes.LimitarCaracteres;
import exceptions.CadastroClienteCpfCadastrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class PainelCadastroCliente extends JPanel {
    private JLabel jlCliente,jlNome,jlGenero,jlEmail,jlEndereco,jlCidade,jlSenha,jlTelefone,jlCpf,jlCep,jlNumeroEndereco;
    private JTextField jtfNome, jtfEmail,jtfEndereco,jtfCidade, jtfTelefone, jtfCpf, jtfCep, jtfNumeroEndereco;
    private JRadioButton jrbFeminino,jrbMasculino;
    private JButton jbCadastrar;
    private ButtonGroup bgGenero;
    private JPasswordField jpfSenha;
    private List<Cliente> clientes;


    //construtor

    public PainelCadastroCliente(List<Cliente> cliente) {
        super();
        this.clientes = cliente;
        setSize(500,400);
        setLayout(null);
        setBackground(Color.WHITE);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        //objetos
        jlCliente = new JLabel("Cadastrar Cliente");
        jlNome = new JLabel("Nome:");
        jtfNome = new JTextField();
        jtfNome.setDocument(new LimitarCaracteres(50,LimitarCaracteres.TipoDadoEntrada.letras));
        jlGenero = new JLabel("Gênero:");
        jrbFeminino = new JRadioButton("Feminino", true);
        jrbMasculino = new JRadioButton("Masculino");
        jrbFeminino.setOpaque(false);
        jrbMasculino.setOpaque(false);
        bgGenero = new ButtonGroup();
        jlEmail = new JLabel("E-mail:");
        jtfEmail = new JTextField();
        jtfEmail.setDocument(new LimitarCaracteres(50,LimitarCaracteres.TipoDadoEntrada.email));
        jlEndereco = new JLabel("Endereço:");
        jtfEndereco = new JTextField();
        jlNumeroEndereco = new JLabel("Número:");
        jtfNumeroEndereco = new JTextField();
        jtfNumeroEndereco.setDocument(new LimitarCaracteres(4,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jlCidade = new JLabel("Cidade:");
        jtfCidade = new JTextField();
        jtfCidade.setDocument(new LimitarCaracteres(20,LimitarCaracteres.TipoDadoEntrada.letras));
        jlSenha = new JLabel("Senha:");
        jpfSenha = new JPasswordField();
        jlTelefone = new JLabel("Telefone:");
        jtfTelefone = new JTextField();
        jtfTelefone.setDocument(new LimitarCaracteres(11,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jlCpf = new JLabel("CPF:");
        jtfCpf = new JTextField();
        jtfCpf.setDocument(new LimitarCaracteres(11,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jlCep = new JLabel("CEP:");
        jtfCep = new JTextField();
        jtfCep.setDocument(new LimitarCaracteres(8,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jbCadastrar = new JButton("Cadastrar");


        //adicionando na tela
        add(jlCliente);
        add(jlNome);
        add(jlGenero);
        add(jlEmail);
        add(jlEndereco);
        add(jlNumeroEndereco);
        add(jlCidade);
        add(jlSenha);
        add(jlTelefone);
        add(jlCpf);
        add(jlCep);
        add(jtfNome);
        add(jtfEmail);
        add(jtfEndereco);
        add(jtfEmail);
        add(jtfNumeroEndereco);
        add(jtfCidade);
        add(jtfCpf);
        add(jtfCep);
        add(jtfTelefone);
        add(jpfSenha);
        add(jrbFeminino);
        add(jrbMasculino);
        add(jbCadastrar);
        bgGenero.add(jrbFeminino);
        bgGenero.add(jrbMasculino);

        //dimensionar componentes
        jlCliente.setBounds(190,10,100,20);
        jlNome.setBounds(20,40,190,20);
        jtfNome.setBounds(20,60,190,20);
        jlSenha.setBounds(270,40,190,20);
        jpfSenha.setBounds(270,60,190,20);
        jlEmail.setBounds(20,90,190,20);
        jtfEmail.setBounds(20,110,190,20);
        jlGenero.setBounds(270,90,190,20);
        jrbFeminino.setBounds(270,110,100,20);
        jrbMasculino.setBounds(370,110,100,20);
        jlCpf.setBounds(20,140,190,20);
        jtfCpf.setBounds(20,160,190,20);
        jlTelefone.setBounds(270,140,190,20);
        jtfTelefone.setBounds(270,160,190,20);
        jlCidade.setBounds(20,190,190,20);
        jtfCidade.setBounds(20,210,190,20);
        jlCep.setBounds(270,190,190,20);
        jtfCep.setBounds(270,210,190,20);
        jlEndereco.setBounds(20,240,370,20);
        jtfEndereco.setBounds(20,260,370,20);
        jlNumeroEndereco.setBounds(400,240,60,20);
        jtfNumeroEndereco.setBounds(400,260,60,20);
        jbCadastrar.setBounds(195,300,100,20);
    }

    private void criarEventos() {
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome,email,endereco,cidade,senha,telefone,cpf;
                int id,cep,numeroEndereco;
                boolean achou = false,genero = true;
                String erro = "Erro: Preencha os seguintes campos corretamente:\n";
                if (!jtfNome.getText().isEmpty() && !jtfEmail.getText().isEmpty() && !jtfEndereco.getText().isEmpty() && !jtfCidade.getText().isEmpty() && !Arrays.toString(jpfSenha.getPassword()).isEmpty() && !jtfTelefone.getText().isEmpty() && !jtfCpf.getText().isEmpty() && !jtfCep.getText().isEmpty() && !jtfNumeroEndereco.getText().isEmpty()){
                    try {
                        nome = jtfNome.getText();
                        if (jrbFeminino.isSelected()) {
                            genero = true;
                        } else if (jrbMasculino.isSelected()) {
                            genero = false;
                        }
                        id = clientes.size()+1;
                        email = jtfEmail.getText();
                        endereco = jtfEndereco.getText();
                        cidade = jtfCidade.getText();
                        senha = Arrays.toString(jpfSenha.getPassword());
                        telefone = jtfTelefone.getText();
                        for (Cliente cliente:clientes) {
                            if (cliente.getCpf().equals(jtfCpf.getText())){
                                achou = true;
                            }
                        }
                        if (!achou){
                            cpf = jtfCpf.getText();
                        }else {
                            throw new CadastroClienteCpfCadastrado();
                        }
                        cep = Integer.parseInt(jtfCep.getText());
                        numeroEndereco = Integer.parseInt(jtfNumeroEndereco.getText());
                        clientes.add(new Cliente(id,nome,genero,email,endereco,cidade,senha,telefone,cpf,cep,numeroEndereco));
                        JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!","Cadastro",JOptionPane.INFORMATION_MESSAGE);
                        jtfNome.setText("");
                        jtfEmail.setText("");
                        jtfEndereco.setText("");
                        jtfCidade.setText("");
                        jpfSenha.setText("");
                        jtfTelefone.setText("");
                        jtfCpf.setText("");
                        jtfCep.setText("");
                        jtfNumeroEndereco.setText("");
                    }catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null,"Erro: Preencha os seguintes campos apenas\n com números inteiros:\n\nCPF\nCEP\nTelefone\nNúmero","ERRO", JOptionPane.ERROR_MESSAGE);
                    } catch (CadastroClienteCpfCadastrado ex) {
                        JOptionPane.showMessageDialog(null,ex,"ERRO",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    if (jtfNome.getText().isEmpty()){
                        erro+="\nNome";
                    }
                    if (jtfEmail.getText().isEmpty()){
                        erro+="\nE-mail";
                    }
                    if (jtfEndereco.getText().isEmpty()){
                        erro+="\nEndereço";
                    }
                    if (jtfCidade.getText().isEmpty()){
                        erro+="\nCidade";
                    }
                    if (Arrays.toString(jpfSenha.getPassword()).isEmpty()){
                        erro+="\nSenha";
                    }
                    if (jtfTelefone.getText().isEmpty()){
                        erro+="\nNome";
                    }
                    if (jtfCpf.getText().isEmpty()){
                        erro+="\nCPF";
                    }
                    if (jtfCep.getText().isEmpty()){
                        erro+="\nCEP";
                    }
                    if (jtfNumeroEndereco.getText().isEmpty()){
                        erro+="\nNúmero de endereço";
                    }
                    JOptionPane.showMessageDialog(null,erro,"ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
