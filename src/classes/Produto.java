package classes;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome, descricao;
    private int id;
    private double preco, peso;
    private boolean disponibilidade;
    public Produto(int id,String nome, String descricao, double preco, double peso, boolean disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.peso = peso;
        this.disponibilidade = disponibilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }
    public String mostrarCadastro(){
        if (this.disponibilidade) {
            return "\n\nID: " + this.id +
                    "\nNome: " + this.nome +
                    "\nDescrição: " + this.descricao +
                    "\nPeso: " + this.descricao +
                    "\nPreço: " + this.preco +
                    "\nDisponibilidade: Sim";
        }else {
            return "\n\nID: " + this.id +
                    "\nNome: " + this.nome +
                    "\nDescrição: " + this.descricao +
                    "\nPeso: " + this.descricao +
                    "\nPreço: " + this.preco +
                    "\nDisponibilidade: Não";
        }

    }
}
