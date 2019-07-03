/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.frontend.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mx.appsw.entity.Usuario;
import lombok.Data;
import mx.appsw.negocios.facade.UsuarioFacade;
import mx.appsw.negocios.integration.ServiceFacadeLocatorSingletonFactory;

/**
 *
 * @author kitto
 */
@Data
public class UsuarioHelper implements Serializable{
    
    private List<Usuario> usuarioList;
    
    public UsuarioHelper(){
        usuarioList=new ArrayList<>();
        setUsuarioList(ServiceFacadeLocatorSingletonFactory.getInstance(UsuarioFacade.class).findAll());
        
    }
    
    public static void main(String[] args) {
        UsuarioHelper helper=new UsuarioHelper();
        System.out.println(""+helper.usuarioList.size());
    }
    public void init(){
        
    }
    
}
