package service;

import DAO.ClienteDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Pedido;

public class ClienteService extends BaseService <Cliente> { 
    
    public ClienteService(){
        dao = new ClienteDAO();
    }
    
    public Cliente getClienteByNome(String str) {
        List<Cliente> listaCliente = getAll(Cliente.class);
        for (Cliente c : listaCliente) {
            if(c.getNome().equals(str)) {
                return c;
            }
        }
        return null;
    }
    public void addPedidoToCliente(Pedido ped) {
        List<Cliente> listaCliente = getAll(Cliente.class);
        for (Cliente c : listaCliente) {
            if (c.getCodigo() == ped.getCliente().getCodigo()) {
                c.addPedido(ped);
            }
        }
    }
    public boolean removePedidoOfCliente(Pedido ped) {
        List<Cliente> listaCliente = getAll(Cliente.class);
        for (Cliente c : listaCliente) {
            if (c.getCodigo() == ped.getCliente().getCodigo()) {
                return c.removePedido(ped);
            }
        }
        return false;
    }
    
    public boolean checkClientes(Cliente cod){
        List<Cliente> listaCliente = getAll(Cliente.class);
        for (Cliente c : listaCliente){
            if(c.getCodigo() == cod.getCodigo()){
                return true;
            }
        }
        return false;
    }
    
}
