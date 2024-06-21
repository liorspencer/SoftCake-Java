package classes;

import java.io.Serializable;

public class Pedido implements Serializable {
    private String nomeCliente;
    private int idPedido,idCliente;
    private double precoTotal;
    private String descricao;
    private boolean ativo;

    public Pedido(String nomeCliente, int idPedido, int idCliente, String descricao, double precoTotal, boolean ativo) {
        this.nomeCliente = nomeCliente;
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.descricao = descricao;
        this.precoTotal = precoTotal;
        this.ativo = ativo;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String mostrarCadastro(){
        if (this.ativo) {
            return "\n\nNome do Cliente: " + this.nomeCliente +
                    "\nID do Pedido: " + this.idPedido +
                    "\nID do Cliente: " + this.idCliente +
                    "\nDescrição do pedido: " + this.descricao +
                    "\nPreço Total: " + precoTotal +
                    "\nEstado do pedido: Ativo";
        }else {
            return "Nome do Cliente: " + this.nomeCliente +
                    "\nID do Pedido: " + this.idPedido +
                    "\nID do Cliente: " + this.idCliente +
                    "\nDescrição do pedido: " + this.descricao +
                    "\nPreço Total: " + this.precoTotal +
                    "\nEstado do pedido: Finalizado";
        }

    }

}
