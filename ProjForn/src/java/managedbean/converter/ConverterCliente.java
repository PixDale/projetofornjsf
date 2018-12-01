/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.converter;

/**
 *
 * @author felipe
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Cliente;
import service.ClienteService;

@FacesConverter("converterCliente")
public class ConverterCliente implements Converter {
	private ClienteService servico = new ClienteService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Cliente e = null;
		if(value != null && !value.equals("")) {
			e = servico.getClienteByNome(value);	
		}
		return e;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object cliente) {
		if (cliente == null || cliente.equals("")) {
			return null;
		} else {
			return ((Cliente)cliente).getNome();
		}
	}
}