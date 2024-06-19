package arquivos;

import classes.Cliente;
import classes.Pedido;
import classes.Produto;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class EscreverLerArquivos {
    public void escreverArquivoCliente(List<Cliente> lista){
        try {
            FileOutputStream fluxo = new FileOutputStream("Cliente.bin");// cria um arquivo chamado Cliente
            ObjectOutputStream objeto = new ObjectOutputStream(fluxo);
            objeto.writeObject(lista);
            objeto.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Cliente> lerObjetoCliente(){
        //Declaração da variável para receber o objeto gravado
        List<Cliente> lista;// variavel para receber o objeto
        try {
            FileInputStream fluxo = new FileInputStream("Cliente.bin"); //ler arquivo
            ObjectInputStream objeto = new ObjectInputStream(fluxo);
            lista = (List<Cliente>) objeto.readObject();
            objeto.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo de clientes não encontrado");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    public void escreverArquivoProduto(List<Produto> lista){
        try {
            FileOutputStream fluxo = new FileOutputStream("Produto.bin");// cria um arquivo chamado Produto
            ObjectOutputStream objeto = new ObjectOutputStream(fluxo);
            objeto.writeObject(lista);
            objeto.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Produto> lerObjetoProduto(){
        //Declaração da variável para receber o objeto gravado
        List<Produto> lista;// variavel para receber o objeto
        try {
            FileInputStream fluxo = new FileInputStream("Produto.bin"); //ler arquivo
            ObjectInputStream objeto = new ObjectInputStream(fluxo);
            lista = (List<Produto>) objeto.readObject();
            objeto.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo de produtos não encontrado");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    public void escreverArquivoPedido(List<Pedido> lista){
        try {
            FileOutputStream fluxo = new FileOutputStream("Pedido.bin");// cria um arquivo chamado Pedido
            ObjectOutputStream objeto = new ObjectOutputStream(fluxo);
            objeto.writeObject(lista);
            objeto.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Pedido> lerObjetoPedido(){
        //Declaração da variável para receber o objeto gravado
        List<Pedido> lista;// variavel para receber o objeto
        try {
            FileInputStream fluxo = new FileInputStream("Pedido.bin"); //ler arquivo
            ObjectInputStream objeto = new ObjectInputStream(fluxo);
            lista = (List<Pedido>) objeto.readObject();
            objeto.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo de pedidos não encontrado");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
