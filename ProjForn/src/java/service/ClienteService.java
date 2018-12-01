/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author 171711
 */
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
    
}
