package modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ProdutoMI")
@DiscriminatorValue("mi")
public class ProdutoMercadoInterno extends Produto implements Serializable{
    private boolean incentivo;

    public ProdutoMercadoInterno(boolean incentivo, int codigo, String nome, Categoria categoria, double preco, int moeda, double imposto) {
        super(codigo, nome, categoria, preco, moeda, imposto);
        this.incentivo = incentivo;
    }

    public ProdutoMercadoInterno(boolean incentivo, String nome, Categoria categoria, double preco, int moeda, double imposto) {
        super(nome, categoria, preco, moeda, imposto);
        this.incentivo = incentivo;
    }

    public ProdutoMercadoInterno() {
    }

    
    
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
