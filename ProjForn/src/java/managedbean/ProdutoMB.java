/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Categoria;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;
import service.CategoriaService;
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
    private Categoria categoriaEscolhida;
    private CategoriaService categoriaService = new CategoriaService();

    public Categoria getCategoriaEscolhida() {
        return categoriaEscolhida;
    }

    public void setCategoriaEscolhida(Categoria categoriaEscolhida) {
        this.categoriaEscolhida = categoriaEscolhida;
    }
    
    public List<Categoria> getCategorias(){
      return categoriaService.getCategorias();
    }
    
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
        
        if(produtoex.getDestino()==null){
            
            produtomi.setCategoria(categoriaEscolhida);
            servico.salvarProduto(produtomi);
            produtomi = new ProdutoMercadoInterno();
        } else {
           
            produtoex.setCategoria(categoriaEscolhida);
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
    public ArrayList<Produto> getProdutos(int tipo){
        return servico.getProdutos(tipo);
    }

    
}
