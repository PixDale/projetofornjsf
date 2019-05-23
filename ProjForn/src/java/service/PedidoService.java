package service;

import DAO.PedidoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;

public class PedidoService extends BaseService <Pedido>{
     private static List<Pedido> listaPedido = new ArrayList<Pedido>();

     public PedidoService(){
         dao = new PedidoDAO();
     }
    public Boolean inserirProduto (ItemPedido ip) {
        //System.out.println("ENTROU NO INSERIR DO SERVICE");
        //System.out.println(ip.getNumeropedido()+" - "+ip.getQuantidade()+" - "+ip.getProduto().getNome());
       for (Pedido p : listaPedido) {
           if(ip.getPedido_IP().getNumero() == p.getNumero()) {
               p.addItens(ip);               
               return true;
           }
       }
       return false;
    }
    
    public int getIndexByCod(int cod){
        for (int i=0; i<listaPedido.size(); i++){
            if(listaPedido.get(i).getNumero() == cod){
                return i;
            }
        }
        return -1;
    }
    
    public boolean checkProduto(Produto produto){
        for(Pedido p : listaPedido){
            for(ItemPedido c : p.getItensPedido()){
                if(c.getProduto().equals(produto))
                    return false;
            }
        }
        return true;
    }
    
    
    
}
