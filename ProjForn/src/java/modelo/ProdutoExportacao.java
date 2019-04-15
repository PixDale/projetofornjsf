package modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ProdutoExportacao")
@DiscriminatorValue("ex")
public class ProdutoExportacao extends Produto implements Serializable{
    private String destino;

    public ProdutoExportacao(String destino, int codigo, String nome, Categoria categoria, double preco, int moeda, double imposto) {
        super(codigo, nome, categoria, preco, moeda, imposto);
        this.destino = destino;
    }

    public ProdutoExportacao(String destino, String nome, Categoria categoria, double preco, int moeda, double imposto) {
        super(nome, categoria, preco, moeda, imposto);
        this.destino = destino;
    }

    public ProdutoExportacao() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
