package classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitarCaracteres extends PlainDocument {
    public enum TipoDadoEntrada {
        NUMERO_INTEIRO, DECIMAL, LETRAS, EMAIL, DATA;
    }
    private int quantCaracteres;
    private TipoDadoEntrada tipoEntrada;
    public LimitarCaracteres(int quantCaracteres, TipoDadoEntrada tipoEntrada) {
        this.quantCaracteres = quantCaracteres;
        this.tipoEntrada = tipoEntrada;
    }
    @Override
    public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
        if (string.equals(null) || getLength() == quantCaracteres) {
            return;
        }
        int totalCaracteres = getLength() + string.length();

        //filtro de carácteres
        String regex = ""; // regular expression (expressão regular)
        switch (tipoEntrada) {
            case NUMERO_INTEIRO:
                regex = "[^0-9]";
                break;
            case DECIMAL:
                regex = "[^0-9,.]";
                break;
            case LETRAS:
                regex = "[^\\p{IsLatin} ]";
                break;
            case EMAIL:
                regex = "[^\\p{IsLatin}@.\\-_] [^0-9]";
                break;
            case DATA:
                regex = "[^0-9/]";
                break;

        }

        //fazendo a substituição
        string = string.replaceAll(regex, "");

        if (totalCaracteres <= quantCaracteres) {
            super.insertString(i, string, as);
        } else {
            String nova = string.substring(0,quantCaracteres);
            super.insertString(i, nova, as);
        }
    }
}
