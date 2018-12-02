package managedbean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

public class ClienteMB implements Serializable{
    private Cliente cliente = new Cliente();
    private ClienteService servico = new ClienteService();
    private Cliente selectedCliente;
    private static int codigogeral = 0;

    public void setSelectedCliente(Cliente c){
        selectedCliente = c;
    }
    
    public Cliente getSelectedCliente(){
        return selectedCliente;
    }
    public void removeSelectedCliente(){
        servico.removerCliente(selectedCliente);
    }
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public void salvarCliente(){
        cliente.setCodigo(++codigogeral);
        servico.salvarCliente(cliente);
        cliente = new Cliente();
    }
    
    public void removerCliente(Cliente cliente){
        if(cliente.getPedidos().isEmpty())
            servico.removerCliente(cliente);
    }
    
    public List<Cliente> getClientes(){
        return servico.getClientes();
    }
    
  

}
