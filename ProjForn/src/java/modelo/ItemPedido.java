package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemPedido implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="PedidoID")
    private Pedido pedido;
    
    private int quantidade;
    
    @OneToOne
    @JoinColumn(name = "ProdutoID", referencedColumnName = "id") //Se der pau trocara id para codigo
    private Produto produto;

    public ItemPedido(int id, Pedido pedido, int quantidade, Produto produto) {
        this.id = id;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemPedido(Pedido pedido, int quantidade, Produto produto) {
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.produto = produto;
    }
    
     public ItemPedido() {
    }
  
    private double totalItem(){
        return produto.getPreco()*quantidade;
    }

    public Pedido getPedido_IP() {
        return pedido;
    }

    public void setPedido_IP(Pedido ped) {
        this.pedido = ped;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
