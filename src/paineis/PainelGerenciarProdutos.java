package paineis;

import classes.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelGerenciarProdutos extends JPanel {
    private List<Produto> produtos;
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

    }
}
