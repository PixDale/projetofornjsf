/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.ItemPedido;

/**
 *
 * @author felipe
 */
@ManagedBean
@ViewScoped
public class ViewMB {
    
  public List<ItemPedido> produtosEscolhidos;

  /*public void showEmployees(ActionEvent ae) {
      String NumPedidoSelecionado = Arrays.stream(getSelectedDepartments())
                                          .collect(Collectors.joining("|"));
      Map<String, List<String>> params = new HashMap<>();
      params.put("selectedDepartments", Arrays.asList(selectedDeptsAsParam));
      RequestContext.getCurrentInstance().openDialog("employee",
              getDialogOptions(), params);
  }
  public Map<String, Object> getDialogOptions() {
      Map<String, Object> options = new HashMap<>();
      options.put("resizable", false);
      options.put("draggable", true);
      options.put("modal", true);
      options.put("height", 400);
      options.put("contentHeight", "100%");
      return options;
  }*/
}
