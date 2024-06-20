package classes;

import java.awt.*;

public class FontesPadrao {
    private final Font fontArialTitulo = new Font("Arial", Font.BOLD, 18);
    private final Font fontArialCorpo = new Font("Arial", Font.PLAIN, 14);
    private final Font fontArialBotao = new Font("Arial", Font.PLAIN, 15);

    public FontesPadrao() {
    }

    public Font fontArialTitulo() {
        return fontArialTitulo;
    }

    public Font fontArialCorpo() {
        return fontArialCorpo;
    }

    public Font fontArialBotao() {
        return fontArialBotao;
    }
}
