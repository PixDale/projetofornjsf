package service;

import DAO.PedidoDAO;
import java.util.List;
import managedbean.GrowlMB;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;

public class PedidoService extends BaseService <Pedido>{

     public PedidoService(){
         dao = new PedidoDAO();
     }
    public Boolean inserirProduto (ItemPedido ip) {
        List<Pedido> listaPedido = getAll(Pedido.class);
        if (checkLimit(ip)){
        for (Pedido p : listaPedido) {
            if(ip.getPedido_IP().getNumero() == p.getNumero()) {
               p.addItens(ip); 
               GrowlMB.success("Produto inserido ao pedido.");
               dao.save(p);
               return true;
           }
        }
        }
        else{
            GrowlMB.error("Erro ao inserir o produto. Cliente não possui limite para isso ou está desativado");
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
        return listaPedido.stream()
                          .noneMatch((p) -> (!p.getItenspedido().stream()
                                  .noneMatch((i) -> (produto.equals(i.getProduto())))));
    }

    private boolean checkLimit(ItemPedido ip) {
        Cliente c = ip.getPedido_IP().getCliente();
        double aux = 0;
        aux += (ip.getProduto().getPreco()*ip.getQuantidade());
        List<Pedido> listaPed = c.getPedidos();
        if (c.getStatus()==0){
            return false;
        }
        for (Pedido p : listaPed){
            List<ItemPedido> lip = p.getItenspedido();
                for (ItemPedido ipe : lip){
                    aux += ip.getQuantidade() * (ip.getProduto().getPreco() + ip.getProduto().getImposto()) * (ip.getProduto().getMoeda());
                }
                
        }
        if (c.getLimite()>=aux)   
            return true;
        return false;
    }
    
    
    
}
