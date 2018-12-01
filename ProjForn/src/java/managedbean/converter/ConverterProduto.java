/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Produto;
import service.ProdutoService;

/**
 *
 * @author Matheus
 */
@FacesConverter("converterProduto")
public class ConverterProduto implements Converter {
	private ProdutoService servico = new ProdutoService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Produto e = null;
		if(value != null && !value.equals("")) {
			e = servico.getProdutoByNome(value);	
		}
		return e;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object produto) {
		if (produto == null || produto.equals("")) {
			return null;
		} else {
			return ((Produto)produto).getNome();
		}
	}
}