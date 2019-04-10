package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Categoria;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;
import org.primefaces.event.RowEditEvent;
import service.CategoriaService;
import service.PedidoService;
import service.ProdutoService;

@ManagedBean
@SessionScoped

public class ProdutoMB {

    private final ProdutoService servico = new ProdutoService();
    private Produto selectedProduto;
    private ProdutoExportacao produtoex = new ProdutoExportacao();
    private ProdutoMercadoInterno produtomi = new ProdutoMercadoInterno();
    private Categoria categoriaEscolhida;
    private final CategoriaService categoriaService = new CategoriaService();
    private final PedidoService pedidoService = new PedidoService();
    private static int codigogeral = 0;

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
            produtomi.setCodigo(++codigogeral);
            produtomi.setCategoria(categoriaEscolhida);
            servico.salvarProduto(produtomi);
            produtomi = new ProdutoMercadoInterno();
        } else {
            produtoex.setCodigo(++codigogeral);
            produtoex.setCategoria(categoriaEscolhida);
            servico.salvarProduto(produtoex);
            produtoex = new ProdutoExportacao();
        }
        System.out.println("alo");
    }
     
    public void removerProduto(Produto produto){
        if(pedidoService.checkProduto(produto))
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
    public List<Produto> getProdutos(int tipo){
        return servico.getProdutos(tipo);
    }
    
    public void onRowEdit(RowEditEvent event) {
       FacesMessage msg = new 
        FacesMessage("Produto Editado",
                ((Produto) event.getObject()).getNome());
       FacesContext.getCurrentInstance().
               addMessage(null, msg);
    }

    
}
