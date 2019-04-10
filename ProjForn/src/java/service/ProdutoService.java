package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;

public class ProdutoService implements Serializable{
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
    public Produto getProdutoByNome(String str) {
        for (Produto c : listaProduto) {
            if(c.getNome().equals(str)) {
                return c;
            }
        }
        return null;
    }
    
    public boolean checkCategoria(Categoria c){
        for(Produto p : listaProduto){
            if(c.equals(p.getCategoria()))
                return false;
        }
        return true;
        
    }
    
 
}
