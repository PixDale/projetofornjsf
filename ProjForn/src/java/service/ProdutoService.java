/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import modelo.Produto;

/**
 *
 * @author 171711
 */
public class ProdutoService {
    private static ArrayList<Produto> listaProduto = new ArrayList<Produto>();
    
    public void salvarProduto(Produto c){
        listaProduto.add(c);
    }
    
    public ArrayList<Produto> getProdutos(){
        return listaProduto;
    }
    
    public void removerProduto(Produto c){
        listaProduto.remove(c);
    }
}
