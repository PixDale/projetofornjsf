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
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;
import service.ProdutoService;

/**
 *
 * @author 171711
 */

@ManagedBean
@SessionScoped

public class ProdutoMB {

    private ProdutoService servico = new ProdutoService();
    private Produto selectedProduto;
    private ProdutoExportacao produtoex = new ProdutoExportacao();
    private ProdutoMercadoInterno produtomi = new ProdutoMercadoInterno();
    
    
    private void removeSelectedProduto(){
        servico.removerProduto(selectedProduto);
    }
    
    public void gerarProduto(){
        System.out.println("Entrou");
        produtoex.setCodigo(2);
        produtoex.setNome("kk");
        produtoex.setDestino("rua");
        servico.salvarProduto(produtoex);
        System.out.println("Saiu");
    }
    
    public void salvarProduto(){
        System.out.println("ola");
        if(produtoex.getDestino().equals(null)){
            servico.salvarProduto(produtomi);
            produtomi = new ProdutoMercadoInterno();
        } else {
            servico.salvarProduto(produtoex);
            produtoex = new ProdutoExportacao();
        }
        System.out.println("alo");
    }
     
    public void removerCliente(Produto produto){
        servico.removerProduto(produto);
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public ProdutoExportacao getProdutoex() {
        return produtoex;
    }

    public void setProdutoex(ProdutoExportacao produtoex) {
        this.produtoex = produtoex;
    }

    public ProdutoMercadoInterno getProdutomi() {
        return produtomi;
    }

    public void setProdutomi(ProdutoMercadoInterno produtomi) {
        this.produtomi = produtomi;
    }
    public ArrayList<Produto> getProdutos(){
        return servico.getProdutos();
    }

    
}
