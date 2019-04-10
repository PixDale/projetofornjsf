package managedbean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Categoria;
import org.primefaces.event.RowEditEvent;
import service.CategoriaService;
import service.ProdutoService;

@ManagedBean
@SessionScoped
public class CategoriaMB implements Serializable{
    private Categoria categoria = new Categoria();
    private CategoriaService servico = new CategoriaService();
    private Categoria selectedCategoria;
    private ProdutoService servicopro = new ProdutoService();
   
  
    public CategoriaMB(){}
    
    public Categoria getCategoria() {
        return categoria;
    }
    public Categoria getSelectedCategoria(){
        return selectedCategoria;
    }
    
    public void setSelectedCategoria(Categoria selected){
        selectedCategoria = selected;
    }
    
    public void removeSelectedCategoria(){
        servico.removerCategoria(selectedCategoria);
        selectedCategoria = null;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void salvarCategoria(){
        if(!categoria.getDescricao().equals("")){
            categoria.setDescricao(categoria.getDescricao().toUpperCase());
            servico.salvarCategoria(categoria);
            categoria = new Categoria();
        }
    }
    
    
    public void removerCategoria(Categoria categoria){
        if(servicopro.checkCategoria(categoria))
        servico.removerCategoria(categoria);
    }
    
    public List<Categoria> getCategorias(){
        return servico.getCategorias();
    }
    
    public void atualizaCat(RowEditEvent event) {
        System.out.println("EVENTO de EDICAO de REGISTRO");
        System.out.println(((Categoria)event.getObject()).getDescricao());
        System.out.println(selectedCategoria.getDescricao());
        
    }
}
