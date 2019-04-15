package managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class GrowlMB {
    
    public static void success(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", msg));
    }
    
    public static void error(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
    }
    
    public static void warning(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", msg));
    }
    
}
