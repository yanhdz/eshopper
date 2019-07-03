/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.frontend.ui;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import mx.appsw.frontend.helper.UsuarioHelper;

/**
 *
 * @author kitto
 */
@Data
@Named
@SessionScoped
public class UsuarioUI implements Serializable {

    private UsuarioHelper helper;
    
    @PostConstruct
    public void init() {
        helper=new UsuarioHelper();
    }
    
    public static void main(String[] args) {
        UsuarioUI ui=new UsuarioUI();
        System.out.println(""+ui.helper.getUsuarioList().size());
    }

}
