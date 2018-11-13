package managedbean;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cliente;
import service.ClienteService;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 171711
 */


@ManagedBean
@SessionScoped

public class ClienteMB {
    private Cliente cliente = new Cliente();
    private ClienteService servico = new ClienteService();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public void salvarCliente(){
        servico.salvarCliente(cliente);
        cliente = new Cliente();
    }
    
    public void removerCliente(Cliente cliente){
        servico.removerCliente(cliente);
    }
    
    public ArrayList<Cliente> getClientes(){
        return servico.getClientes();
    }
    
}
