package classes;

import javax.swing.*;

public class IdBotao extends JButton {
    private int id;

    public IdBotao(String label, int id) {
        super(label);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
