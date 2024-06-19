package exceptions;

public class CadastroClienteCpfCadastrado extends Exception{
    public CadastroClienteCpfCadastrado(){
        super("CPF já cadastrado no sistema.");
    }
}
