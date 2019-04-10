package modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ProdutoExportacao")
@DiscriminatorValue("ex")
public class ProdutoExportacao extends Produto implements Serializable{
    private String destino;

   
    

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
