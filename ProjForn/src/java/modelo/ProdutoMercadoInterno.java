package modelo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 171711
 */
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
