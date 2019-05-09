package service;

import DAO.CategoriaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import modelo.Categoria;

public class CategoriaService {
    CategoriaDAO dao = new CategoriaDAO();
    
    public int salvarCategoria(Categoria c) {
       
        try {
            dao.save(c);
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Categoria no Banco.");
            return -1;
        }
    }


    public List<Categoria> getCategorias() {
        try {
            List<Categoria> categs = dao.getAll(Categoria.class);
            return categs;
        } catch (Exception e) {
            System.out.println("Não foi possível obter as Categorias do banco. - "+e.getMessage());
            return null;
        } 
    }

    public int removerCategoria(Categoria c) {
        
        try {
            dao.remove(c);
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao remover categoria do banco. - "+e.getMessage());
            return -1;
        }
    }

    public Categoria getCategoriaByNome(String value) {
        List<Categoria> listaCat = getCategorias();
        for (Categoria e : listaCat) {
            if (e.getDescricao().equals(value)) {
                return e;
            }
        }
        return null;
    }

}
