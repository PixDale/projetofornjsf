package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Categoria implements Serializable{
    //@Id
    private int id;
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
