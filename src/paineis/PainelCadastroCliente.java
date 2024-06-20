package paineis;

import classes.Cliente;
import classes.ConexaoCep;
import classes.FontesPadrao;
import classes.LimitarCaracteres;
import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.CadastroClienteCpfCadastrado;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PainelCadastroCliente extends JPanel {
    private ConexaoCep conexaoCep;
    private FontesPadrao fontes;
    private MaskFormatter mfCep, mfCpf, mfTelefone;
    private JLabel jlCliente,jlNome,jlGenero,jlEmail,jlEndereco,jlBairro,jlCidade,jlEstado,jlSenha,jlTelefone,jlCpf,jlCep,jlNumeroEndereco;
    private JTextField jtfNome, jtfEmail,jtfEndereco,jtfBairro,jtfCidade,jtfNumeroEndereco;
    private JComboBox<String> jcbEstado;
    private String[] estados = {"--","AC","AL","AP","AM","BA","CE","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO","DF"};
    private JFormattedTextField jtfCep,jtfCpf,jtfTelefone;
    private JRadioButton jrbFeminino,jrbMasculino;
    private JButton jbCadastrar, jbBuscarCep;
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
        try {
            mfCep = new MaskFormatter("#####-###");
            mfCpf = new MaskFormatter("###.###.###-##");
            mfTelefone = new MaskFormatter("(##)######-####");
            mfCep.setPlaceholderCharacter('_');
            mfCpf.setPlaceholderCharacter('_');
            mfTelefone.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        fontes = new FontesPadrao();
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
        jlBairro = new JLabel("Bairro:");
        jtfBairro = new JTextField();
        jtfNumeroEndereco.setDocument(new LimitarCaracteres(4,LimitarCaracteres.TipoDadoEntrada.numeroInteiro));
        jlCidade = new JLabel("Cidade:");
        jtfCidade = new JTextField();
        jtfCidade.setDocument(new LimitarCaracteres(20,LimitarCaracteres.TipoDadoEntrada.letras));
        jlEstado = new JLabel("Estado:");
        jcbEstado = new JComboBox<>(estados);
        jlSenha = new JLabel("Senha:");
        jpfSenha = new JPasswordField();
        jlTelefone = new JLabel("Telefone:");
        jtfTelefone = new JFormattedTextField(mfTelefone);
        jlCpf = new JLabel("CPF:");
        jtfCpf = new JFormattedTextField(mfCpf);
        jlCep = new JLabel("CEP:");
        jtfCep = new JFormattedTextField(mfCep);
        jbBuscarCep = new JButton("Buscar CEP");
        jbCadastrar = new JButton("Cadastrar");


        //adicionando na tela
        add(jlCliente);
        add(jlNome);
        add(jlGenero);
        add(jlEmail);
        add(jlEndereco);
        add(jlBairro);
        add(jlNumeroEndereco);
        add(jlCidade);
        add(jlEstado);
        add(jlSenha);
        add(jlTelefone);
        add(jlCpf);
        add(jlCep);
        add(jtfNome);
        add(jtfEmail);
        add(jtfEndereco);
        add(jtfBairro);
        add(jtfEmail);
        add(jtfNumeroEndereco);
        add(jtfCidade);
        add(jcbEstado);
        add(jtfCpf);
        add(jtfCep);
        add(jbBuscarCep);
        add(jtfTelefone);
        add(jpfSenha);
        add(jrbFeminino);
        add(jrbMasculino);
        add(jbCadastrar);
        bgGenero.add(jrbFeminino);
        bgGenero.add(jrbMasculino);

        //estilizar componentes
        jlCliente.setFont(fontes.fontArialTitulo());
        jlCliente.setHorizontalAlignment(SwingConstants.CENTER);
        jlNome.setFont(fontes.fontArialCorpo());
        jtfNome.setFont(fontes.fontArialCorpo());
        jlGenero.setFont(fontes.fontArialCorpo());
        jrbMasculino.setFont(fontes.fontArialCorpo());
        jrbFeminino.setFont(fontes.fontArialCorpo());
        jlEmail.setFont(fontes.fontArialCorpo());
        jtfEmail.setFont(fontes.fontArialCorpo());
        jlSenha.setFont(fontes.fontArialCorpo());
        jpfSenha.setFont(fontes.fontArialCorpo());
        jlCpf.setFont(fontes.fontArialCorpo());
        jtfCpf.setFont(fontes.fontArialCorpo());
        jlTelefone.setFont(fontes.fontArialCorpo());
        jtfTelefone.setFont(fontes.fontArialCorpo());
        jlCep.setFont(fontes.fontArialCorpo());
        jtfCep.setFont(fontes.fontArialCorpo());
        jbBuscarCep.setFont(fontes.fontArialBotao());
        jlCidade.setFont(fontes.fontArialCorpo());
        jtfCidade.setFont(fontes.fontArialCorpo());
        jlEstado.setFont(fontes.fontArialCorpo());
        jcbEstado.setFont(fontes.fontArialCorpo());
        jlEndereco.setFont(fontes.fontArialCorpo());
        jtfEndereco.setFont(fontes.fontArialCorpo());
        jlNumeroEndereco.setFont(fontes.fontArialCorpo());
        jtfNumeroEndereco.setFont(fontes.fontArialCorpo());
        jlBairro.setFont(fontes.fontArialCorpo());
        jtfBairro.setFont(fontes.fontArialCorpo());
        jbCadastrar.setFont(fontes.fontArialBotao());

        //dimensionar componentes
        jlCliente.setBounds(0,10,485,20);
        jlNome.setBounds(20,40,190,20);
        jtfNome.setBounds(20,60,194,30);
        jlGenero.setBounds(270,40,190,20);
        jrbFeminino.setBounds(270,60,100,30);
        jrbMasculino.setBounds(370,60,100,30);
        jlEmail.setBounds(20,100,190,20);
        jtfEmail.setBounds(20,120,194,30);
        jlSenha.setBounds(270,100,190,20);
        jpfSenha.setBounds(270,120,194,30);
        jlCpf.setBounds(20,160, 115,20);
        jtfCpf.setBounds(20,180,115,30);
        jlTelefone.setBounds(140,160,123,20);
        jtfTelefone.setBounds(140,180,123,30);
        jlCep.setBounds(268,160,82,20);
        jtfCep.setBounds(268,180,82,30);
        jbBuscarCep.setBounds(354,180,111,30);
        jlCidade.setBounds(92,220,374,20);
        jtfCidade.setBounds(92,240,374,30);
        jlEstado.setBounds(20,220,52,20);
        jcbEstado.setBounds(20,240,52,30);
        jlEndereco.setBounds(20,280,370,20);
        jtfEndereco.setBounds(20,300,370,30);
        jlNumeroEndereco.setBounds(400,280,60,20);
        jtfNumeroEndereco.setBounds(400,300,60,30);
        jlBairro.setBounds(20,340,446,20);
        jtfBairro.setBounds(20,360,446,30);
        jbCadastrar.setBounds(195,400,100,30);
    }

    private void criarEventos() {
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome,email,endereco,cidade,estado,senha,telefone,cpf,cep;
                int id;
                boolean achou = false,genero = true;
                String erro = "Erro: Preencha os seguintes campos corretamente:\n";
                if (!jtfNome.getText().isEmpty() && !jtfEmail.getText().isEmpty() && !jtfEndereco.getText().isEmpty() && !jtfCidade.getText().isEmpty() && !jtfBairro.getText().isEmpty() && !jcbEstado.getSelectedItem().toString().equals("--")  && !Arrays.toString(jpfSenha.getPassword()).isEmpty() && !jtfTelefone.getText().contains("_") && !jtfCpf.getText().contains("_") && !jtfCep.getText().contains("_") && !jtfNumeroEndereco.getText().isEmpty()){
                    try {
                        nome = jtfNome.getText();
                        if (jrbFeminino.isSelected()) {
                            genero = true;
                        } else if (jrbMasculino.isSelected()) {
                            genero = false;
                        }
                        id = clientes.size()+1;
                        email = jtfEmail.getText();
                        endereco = jtfEndereco.getText()+", "+jtfNumeroEndereco.getText()+", "+jtfBairro.getText();
                        cidade = jtfCidade.getText();
                        estado = jcbEstado.getSelectedItem().toString();
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
                        cep = jtfCep.getText();
                        clientes.add(new Cliente(id,nome,genero,email,endereco,cidade,estado,senha,telefone,cpf,cep));
                        JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!","Cadastro",JOptionPane.INFORMATION_MESSAGE);
                        jtfNome.setText("");
                        jtfEmail.setText("");
                        jtfEndereco.setText("");
                        jtfCidade.setText("");
                        jcbEstado.setSelectedItem("--");
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
                    if (jcbEstado.getSelectedItem().toString().equals("--")){
                        erro+="\nEstado";
                    }
                    if (Arrays.toString(jpfSenha.getPassword()).isEmpty()){
                        erro+="\nSenha";
                    }
                    if (jtfTelefone.getText().contains("_")){
                        erro+="\nNome";
                    }
                    if (jtfCpf.getText().contains("_")){
                        erro+="\nCPF";
                    }
                    if (jtfCep.getText().contains("_")){
                        erro+="\nCEP";
                    }
                    if (jtfNumeroEndereco.getText().isEmpty()){
                        erro+="\nNúmero de endereço";
                    }
                    JOptionPane.showMessageDialog(null,erro,"ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jbBuscarCep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            conexaoCep = new ConexaoCep(jtfCep.getText());
                            jcbEstado.setSelectedItem(conexaoCep.getEstadoBuscado());
                            jtfCidade.setText(conexaoCep.getCidadeBuscado());
                            jtfBairro.setText(conexaoCep.getBairroBuscado());
                            jtfEndereco.setText(conexaoCep.getEnderecoBuscado());
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        } catch (ExecutionException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        } catch (JsonProcessingException ex) {
                            throw new RuntimeException(ex);
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(null,"CEP inválido.","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                t.start();
            }
        });
    }

}
