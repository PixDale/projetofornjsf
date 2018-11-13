package modelo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 171711
 */
public class ItemPedido {
    private int numero;
    private int quantidade;
    private Produto produto;
    
    private double totalItem(){
        return produto.getPreco()*quantidade;
    }
}
