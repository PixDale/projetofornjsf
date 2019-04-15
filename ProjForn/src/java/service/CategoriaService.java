package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import modelo.Categoria;

public class CategoriaService {
    
    public EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjFornPU");
        return emf.createEntityManager();
    }
    
    public int salvarCategoria(Categoria c) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Categoria no Banco.");
            return -1;
        } finally {
            em.close();
        }
    }


    public List<Categoria> getCategorias() {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("Select cat From Categoria cat");
            List<Categoria> categs = q.getResultList();
            em.getTransaction().commit();
            return categs;
        } catch (Exception e) {
            System.out.println("Não foi possível obter as Categorias do banco. - "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public int removerCategoria(Categoria c) {
        EntityManager em = getEM();
        try {
            c = em.find(Categoria.class, c.getId());
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao remover categoria do banco. - "+e.getMessage());
            return -1;
        } finally {
            em.close();
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
