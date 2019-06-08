/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.BaseDAO;
import java.util.List;
/**
 *
 * @author felipe
 * @param <T>
 */
public abstract class BaseService <T> {
    public BaseDAO dao;

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }

    public int salvar(T t) {
        try {
            dao.save(t);
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao salvar "+t.getClass().getName()+" no Banco.");
            return -1;
        }
    }
    public List<T> getAll(Class<T> classe) {
        try {
            List<T> categs = dao.getAll(classe);
            return categs;
        } catch (Exception e) {
            System.out.println("Não foi possível obter "+classe.getSimpleName()+" do banco. - "+e.getMessage());
            return null;
        } 
    }
    public int remover(T t) {
        
        try {
            dao.remove(t);
            return 0;
        } catch (Exception e) {
            System.out.println("Erro ao remover "+t.getClass().getName()+" do banco. - "+e.getMessage());
            return -1;
        }
    }
    
    
}
