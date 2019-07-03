package mx.appsw.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author kitto
 */
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_permission;

    @Basic
    private String permiso;

    @Basic
    private boolean ver;

    @Basic
    private boolean agregar;

    @Basic
    private boolean editar;

    @Basic
    private boolean borrar;

    public Long getId_permission() {
        return id_permission;
    }

    public void setId_permission(Long id_permission) {
        this.id_permission = id_permission;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isBorrar() {
        return borrar;
    }

    public void setBorrar(boolean borrar) {
        this.borrar = borrar;
    }

}