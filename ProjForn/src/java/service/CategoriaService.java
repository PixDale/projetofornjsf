package service;
import DAO.CategoriaDAO;
import java.util.List;
import modelo.Categoria;
public class CategoriaService extends BaseService<Categoria> {
    public CategoriaService() {
        dao = new CategoriaDAO();
    }
    public Categoria getCategoriaByNome(String value) {
        List<Categoria> listaCat = getAll(Categoria.class);
        for (Categoria e : listaCat) { 
            if (e.getDescricao().equals(value)) {
                return e;
            }
        }
        return null;
    }
}