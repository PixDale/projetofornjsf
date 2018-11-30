/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;

/**
 *
 * @author 171711
 */
public class ProdutoService {
    private static ArrayList<Produto> listaProduto = new ArrayList<Produto>();
    
    public void salvarProduto(Produto c){
        listaProduto.add(c);
    }
    
    public ArrayList getProdutos(int tipo){
        if (tipo == 1) {
            ArrayList<ProdutoExportacao> listaux = new ArrayList();
            for (Produto p : listaProduto) {
                if(p instanceof ProdutoExportacao) {
                    listaux.add((ProdutoExportacao) p);
                }
            }
            return listaux;
        } else if (tipo == 2) {
            ArrayList<ProdutoMercadoInterno> listaux = new ArrayList();
            for (Produto p : listaProduto) {
                if(p instanceof ProdutoMercadoInterno) {
                    listaux.add((ProdutoMercadoInterno) p);
                }
            }
            return listaux;
        }
        return listaProduto;
    }
    
    public void removerProduto(Produto c){
        listaProduto.remove(c);
    }
}
