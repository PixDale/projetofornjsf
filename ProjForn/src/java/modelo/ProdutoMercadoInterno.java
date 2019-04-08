package modelo;

public class ProdutoMercadoInterno extends Produto{
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
