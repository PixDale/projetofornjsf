package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Pedido;

public class ClienteService {
    private static List<Cliente> listaCliente = new ArrayList<Cliente>();
    
    public void salvarCliente(Cliente c){
        listaCliente.add(c);
    }
    
    public List<Cliente> getClientes(){
        return listaCliente;
    }

    public void removerCliente(Cliente c){
        listaCliente.remove(c);
    }
    public Cliente getClienteByNome(String str) {
        for (Cliente c : listaCliente) {
            if(c.getNome().equals(str)) {
                return c;
            }
        }
        return null;
    }
    public void addPedidoToCliente(Pedido ped) {
        for (Cliente c : listaCliente) {
            if (c.getCodigo() == ped.getCliente().getCodigo()) {
                c.addPedido(ped);
            }
        }
    }
    public boolean removePedidoOfCliente(Pedido ped) {
        for (Cliente c : listaCliente) {
            if (c.getCodigo() == ped.getCliente().getCodigo()) {
                return c.removePedido(ped);
            }
        }
        return false;
    }
    
    public boolean checkClientes(Cliente cod){
        for (Cliente c : listaCliente){
            if(c.getCodigo() == cod.getCodigo()){
                return true;
            }
        }
        return false;
    }
    
}
