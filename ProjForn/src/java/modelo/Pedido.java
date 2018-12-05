package modelo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 171711
 */
public class Pedido {
    private int numero;
   
    private Date data = new Date();
    private List<ItemPedido> itenspedido = new ArrayList<>();
    private Cliente cliente;
   
    public int getNumero() {
        return numero;
        
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        //DateFormat outputFormat = new SimpleDateFormat("MM/yyyy", Locale.pt_BR);
        //DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.pt_BR);
        //return formato.format(data);
        return data;
        
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemPedido> getItensPedido() {
        return itenspedido;
    }
    
    public void addItens(ItemPedido item) {
        itenspedido.add(item);
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itenspedido = itens;
    }
    
    
    
}
