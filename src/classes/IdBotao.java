package classes;

import javax.swing.*;

public class IdBotao extends JButton {
    private int id;
    private int opcao;
    public IdBotao(String label, int id) {
        super(label);
        this.id = id;
    }
    public IdBotao(String label, int id, int opcao) {
        super(label);
        this.id = id;
        this.opcao = opcao;
    }

    public int getId() {
        return id;
    }

    public int getOpcao() {
        return opcao;
    }
}
