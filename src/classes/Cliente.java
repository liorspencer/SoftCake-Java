package classes;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome,email,endereco,cidade,senha,telefone,cpf;
    private int cep,numeroEndereco,id;
    private boolean genero;

        public Cliente(int id, String nome,boolean genero, String email, String endereco, String cidade, String senha, String telefone, String cpf, int cep, int numeroEndereco) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
    }

    public int getId() {
        return id;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public int getCep() {
        return cep;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }
    public String mostrarCadastro(){
        if (this.genero) {
            return "\n\nID: " + this.id+
                    "\nNome: " + this.nome +
                    "\nGênero: Feminino" +
                    "\nE-mail: " + this.email +
                    "\nEndereço: " + this.endereco +
                    "\nCidade: " + this.cidade +
                    "\nTelefone: " + this.telefone +
                    "\nCPF: " + this.cpf +
                    "\nCEP: " + this.cep +
                    "\nNúmero de endereço: " + this.numeroEndereco;
        }else{
            return "\n\nID: " + this.id+
                    "\nNome: " + this.nome +
                    "\nGênero: Masculino" +
                    "\nE-mail: " + this.email +
                    "\nEndereço: " + this.endereco +
                    "\nCidade: " + this.cidade +
                    "\nTelefone: " + this.telefone +
                    "\nCPF: " + this.cpf +
                    "\nCEP: " + this.cep +
                    "\nNúmero de endereço: " + this.numeroEndereco;
        }
    }
}
