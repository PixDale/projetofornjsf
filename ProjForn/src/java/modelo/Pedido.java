package modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int numero;
   
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @OneToMany(mappedBy="pedido")
    private List<ItemPedido> itenspedido;
    
    private Cliente cliente;

    public Pedido(int numero, Cliente cliente, Date data) {
        this.data = new Date();
        this.itenspedido = new ArrayList();
        this.data = data;
        this.numero = numero;
        this.cliente = cliente;
    }

    public Pedido(Cliente cliente, Date data) {
        this.data = new Date();
        this.itenspedido = new ArrayList();
        this.data = data;
        this.cliente = cliente;
    }
   
     public Pedido() {
        this.data = new Date();
        this.itenspedido = new ArrayList();
    }
    
    public int getNumero() {
        return numero;
        
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        //return formato.format(data);
        //return outputFormat.format(data);
        return data;
        
    }

    public void setData(Date data) {
        this.data = data;
    }
    //OneToMany(mappedBy = "pedido", cascade = CascadeType.REFRESH)
    public List<ItemPedido> getItenspedido() {
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
