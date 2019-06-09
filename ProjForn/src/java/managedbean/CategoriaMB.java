package managedbean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Categoria;
import org.primefaces.event.RowEditEvent;
import service.CategoriaService;
import service.ProdutoService;

@ManagedBean
@SessionScoped
public class CategoriaMB {

    private Categoria categoria = new Categoria();
    private List<Categoria> listaCat;

    private final CategoriaService servico = new CategoriaService();
    private final ProdutoService servicoproduto = new ProdutoService();

    @PostConstruct
    public void init() {
        listaCat = servico.getAll(Categoria.class); // Call the DB here.
    }

    public List<Categoria> getCategorias() {
        return listaCat;
    }

    public Categoria getCategoria() {
        return categoria;
    }

//    public Categoria getSelectedCategoria() {
//        return selectedCategoria;
//    }
//    public void setSelectedCategoria(Categoria selected) {
//        selectedCategoria = selected;
//    }
//    public void removeSelectedCategoria() {
//        servico.removerCategoria(selectedCategoria);
//        selectedCategoria = null;
//    }
    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }

    public void salvarCategoria() {
        if (!categoria.getDescricao().equals("")) {
            categoria.setDescricao(categoria.getDescricao().toUpperCase());
            servico.salvar(categoria);
            categoria = new Categoria();
            init();
        }
    }

    public void removerCategoria(Categoria categoria) {
        if (servico.remover(categoria) == 0) {
            GrowlMB.success("Categoria removida com sucesso");
            init();
        } else {
            GrowlMB.error("Não foi possível remover essa categoria, verifique se não há produtos associados.");
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Categoria c = (Categoria) event.getObject();
            System.out.println("ID= " + c.getId() + " = Desc= " + c.getDescricao());
            if (!c.getDescricao().equals("")) {

                c.setDescricao(c.getDescricao().toUpperCase());
                servico.salvar(c);
                init();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            init();
        }
    }
}
