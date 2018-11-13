/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import modelo.ItemPedido;
import modelo.Pedido;

/**
 *
 * @author 171711
 */
public class PedidoService {
     private static ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
     
        
     public void salvarPedido(Pedido c){
        listaPedido.add(c);
    }
    
    public ArrayList<Pedido> getPedidos(){
        return listaPedido;
    }
    
    public void removerPedido(Pedido c){
        listaPedido.remove(c);
    }
    public void inserirProduto (ItemPedido ip) {
       
    }
    public int getIndexByCod(int cod){
        for (int i=0; i<listaPedido.size(); i++){
            if(listaPedido.get(i).getNumero() == cod){
                return i;
            }
        }
        return -1;
    }
    
}
