package paineis;

import telas.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PainelLogin extends JPanel {
    private JLabel jlLogin, jlUsuario, jlSenha, jlImagem;
    private JTextField jtfUsuario;
    private JPasswordField jpfSenha;
    private JButton jbCadastrar, jbLogin;
    private String usuario,senha;
    private ImageIcon imagem;

    //construtor

    public PainelLogin() {
        setSize(500,400);
        setLayout(null);
        setBackground(Color.white);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        //objetos
        jlLogin = new JLabel("Autenticação");
        jlUsuario = new JLabel("Usuário");
        jlSenha = new JLabel("Senha");
        jtfUsuario = new JTextField();
        jpfSenha = new JPasswordField();
        jbLogin = new JButton("Entrar");
        jbCadastrar = new JButton("Cadastrar");
        imagem = new ImageIcon(getClass().getResource("/imagem/proj.png"));
        jlImagem = new JLabel(imagem);

        //adicionar na tela
        add(jlLogin);
        add(jlUsuario);
        add(jlSenha);
        add(jtfUsuario);
        add(jpfSenha);
        add(jbLogin);
        add(jbCadastrar);
        add(jlImagem);

        //dimensionar os componentes na tela
        jlImagem.setBounds(0,0,500,400);
        jlLogin.setBounds(210,50,80,20);
        jlUsuario.setBounds(150,110,60,20);
        jlSenha.setBounds(150,170,60,20);
        jtfUsuario.setBounds(150,130,200,20);
        jpfSenha.setBounds(150,190,200,20);
        jbLogin.setBounds(190,240,120,20);
        jbCadastrar.setBounds(190,265,120,20);

        jlLogin.setForeground(Color.WHITE);
        jlUsuario.setForeground(Color.WHITE);
        jlSenha.setForeground(Color.WHITE);

    }

    private void criarEventos() {
        jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtfUsuario.getText().equals(usuario) && String.valueOf(jpfSenha.getPassword()).equals(senha)){
                    JOptionPane.showMessageDialog(null,"Autenticado com sucesso!","Confirmação",JOptionPane.INFORMATION_MESSAGE);
                    TelaPrincipal.desbloquearPrograma();
                } else {
                    JOptionPane.showMessageDialog(null,"Usuário/Senha inválidos.","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jtfUsuario.getText().isEmpty() && !String.valueOf(jpfSenha.getPassword()).isEmpty()){
                    usuario = jtfUsuario.getText();
                    senha = String.valueOf(jpfSenha.getPassword());
                    jtfUsuario.setText("");
                    jpfSenha.setText("");
                    JOptionPane.showMessageDialog(null,"Usuário Cadastrado!","Confirmação",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Impossível cadastrar, pois há campos não preenchidos.","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
