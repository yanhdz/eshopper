/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.negocios.facade;

import java.util.List;
import mx.appsw.entity.Usuario;
import mx.appsw.negocios.delegate.UsuarioDelegate;

/**
 *
 * @author kitto
 */
public class UsuarioFacade {
    
    private UsuarioDelegate delegate;
    
    public UsuarioFacade(){
        delegate=new UsuarioDelegate();
    }
    
    public void save(Usuario usuario){
        delegate.save(usuario);
    }
    
    public List<Usuario> findAll(){
        return delegate.findAll();
    }
    
    
    
}
