package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<ProdutoExportacao> listaEx;
    private List<ProdutoMercadoInterno> listaMi;
    

    @PostConstruct
    public void init() {
        listaEx = servico.getProdutos(1);
        listaMi = servico.getProdutos(2);// Call the DB here.
    }
    public Categoria getCategoriaEscolhida() {
        return categoriaEscolhida;
    }

    public void setCategoriaEscolhida(Categoria categoriaEscolhida) {
        this.categoriaEscolhida = categoriaEscolhida;
    }
    
    public List<Categoria> getCategorias(){
      return categoriaService.getAll(Categoria.class);
    }
    
    public void salvarProduto(){
        try{
        if(produtoex.getDestino()==null){
            System.out.println(categoriaEscolhida.getDescricao());
            produtomi.setCategoria(categoriaEscolhida);
            servico.salvar(produtomi);
            produtomi = new ProdutoMercadoInterno();
        } else {
            System.out.println(categoriaEscolhida.getDescricao());
            produtoex.setCategoria(categoriaEscolhida);
            servico.salvar(produtoex);
            produtoex = new ProdutoExportacao();
        }
            GrowlMB.success("Produto salvo com sucesso");
        }catch(Exception e){
            GrowlMB.error("Não foi possivel salvar o produto");
        }
        finally{
            init();
        }
    }
     
    public void removerProduto(Produto produto){
        if (pedidoService.checkProduto(produto)){
            servico.remover(produto);
            GrowlMB.success("Produto removido com sucesso");
        }
        else{
            GrowlMB.error("Não foi possível remover o produto, verifique se não está associado a nenhum pedido");
        }
        init();
        
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
    public List<ProdutoExportacao> getProdutosEx(){
            return listaEx;
    }
    public List<ProdutoMercadoInterno> getProdutosMi(){
            return listaMi;
    }
    

    public void onRowEdit(RowEditEvent event) {
        try {
            Produto c = (Produto) event.getObject();
            if (!c.getNome().equals("")) {
                c.setNome(c.getNome().toUpperCase());
                servico.salvar(c);
                init();
                GrowlMB.success("Produto alterado com sucesso");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            GrowlMB.error("Não foi possível alterar o produto");
        } finally {
            init();
        }
    }

    
}
