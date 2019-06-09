package service;

import DAO.PedidoDAO;
import java.util.List;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;

public class PedidoService extends BaseService <Pedido>{

     public PedidoService(){
         dao = new PedidoDAO();
     }
    public Boolean inserirProduto (ItemPedido ip) {
        List<Pedido> listaPedido = getAll(Pedido.class);
        for (Pedido p : listaPedido) {
            if(ip.getPedido_IP().getNumero() == p.getNumero()) {
               p.addItens(ip);               
               return true;
           }
       }
       return false;
    }
    
    public int getIndexByCod(int cod){
        List<Pedido> listaPedido = getAll(Pedido.class);
        for (int i=0; i<listaPedido.size(); i++){
            if(listaPedido.get(i).getNumero() == cod){
                return i;
            }
        }
        return -1;
    }
    //Verifica se o produto existe na lista de pedidos
    public boolean checkProduto(Produto produto){
        List<Pedido> listaPedido = getAll(Pedido.class);
<<<<<<< HEAD
        for(Pedido p : listaPedido){
            return !p.getItenspedido().stream().noneMatch((c) -> (c.getProduto().equals(produto)));
        }
        return true;
=======
        return listaPedido.stream().noneMatch((p) -> (!p.getItensPedido().stream().noneMatch((i) -> (produto.equals(i.getProduto())))));
>>>>>>> master
    }
    
    
    
}
