package classes;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String senha;
    private String telefone;
    private String cpf;
    private String cep;
    private int id;
    private boolean genero;

        public Cliente(int id, String nome,boolean genero, String email, String endereco, String cidade, String estado, String senha, String telefone, String cpf, String cep) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cep = cep;
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

    public String mostrarCadastro(){
        if (this.genero) {
            return "\n\nID: " + this.id+
                    "\nNome: " + this.nome +
                    "\nGênero: Feminino" +
                    "\nE-mail: " + this.email +
                    "\nEndereço: " + this.endereco +
                    "\nEstado: " + this.estado +
                    "\nCidade: " + this.cidade +
                    "\nTelefone: " + this.telefone +
                    "\nCPF: " + this.cpf +
                    "\nCEP: " + this.cep;
        }else{
            return "\n\nID: " + this.id+
                    "\nNome: " + this.nome +
                    "\nGênero: Masculino" +
                    "\nE-mail: " + this.email +
                    "\nEndereço: " + this.endereco +
                    "\nEstado: " + this.estado +
                    "\nCidade: " + this.cidade +
                    "\nTelefone: " + this.telefone +
                    "\nCPF: " + this.cpf +
                    "\nCEP: " + this.cep;
        }
    }
}
