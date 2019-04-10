package modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ProdutoMI")
@DiscriminatorValue("mi")
public class ProdutoMercadoInterno extends Produto implements Serializable{
    private boolean incentivo;

    public boolean isIncentivo() {
        return incentivo;
    }


    public String getIncentivo() {
        if (incentivo)
            return "Possui Incentivo";
        return "Não Possui Incentivo";
    }

    public void setIncentivo(boolean incentivo) {
        this.incentivo = incentivo;
    }
    
}
