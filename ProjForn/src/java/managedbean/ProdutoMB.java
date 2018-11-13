/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Produto;
import service.ProdutoService;

/**
 *
 * @author 171711
 */

@ManagedBean
@SessionScoped

public class ProdutoMB {
    private Produto produto = new Produto();
    private ProdutoService servico = new ProdutoService();
    
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    public void salvarCliente(){
        servico.salvarProduto(produto);
        produto = new Produto();
    }
    
    public void removerCliente(Produto produto){
        servico.removerProduto(produto);
    }
    
    public ArrayList<Produto> getProdutos(){
        return servico.getProdutos();
    }
    
}
