package service;

import DAO.ClienteDAO;
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
        listaCliente.stream()
                    .filter((c) -> (c.getCodigo() == ped.getCliente().getCodigo()))
                    .forEachOrdered((c) -> c.addPedido(ped));
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
    //Verifica se um cliente ja está cadastrado comparando os códigos.
    public boolean checkClientes(Cliente cod){
        List<Cliente> listaCliente = getAll(Cliente.class);
        return listaCliente.stream().anyMatch((c) -> (c.getCodigo() == cod.getCodigo()));
    }
    
}
