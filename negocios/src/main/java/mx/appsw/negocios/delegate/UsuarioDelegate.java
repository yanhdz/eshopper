/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.appsw.negocios.delegate;

import java.util.List;
import mx.appsw.entity.Usuario;
import mx.appsw.persistencia.dao.UsuarioDAO;
import mx.appsw.persistencia.integration.ServiceLocatorSingletonFactory;

/**
 *
 * @author kitto
 */
public class UsuarioDelegate {

    public void save(Usuario usuario) {
        ServiceLocatorSingletonFactory.getInstance(UsuarioDAO.class).save(usuario);
    }

    public List<Usuario> findAll() {
        return ServiceLocatorSingletonFactory.getInstance(UsuarioDAO.class).findAll();
    }

    public Usuario find(Integer id) {
        return ServiceLocatorSingletonFactory.getInstance(UsuarioDAO.class).find(id);
    }

}
