/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package managedbean;


import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pedido;
import service.PedidoService;

/**
 *
 * @author 171711
 */
@ManagedBean
@SessionScoped

public class PedidoMB {
    private Pedido pedido = new Pedido();
    private PedidoService servico = new PedidoService();
    
    
      public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    public void salvarPedido(){
        servico.salvarPedido(pedido);
        pedido = new Pedido();
    }
    
    public void removerPedido(Pedido pedido){
        servico.removerPedido(pedido);
    }
    
    public ArrayList<Pedido> getPedidos(){
        return servico.getPedidos();
    }
}
