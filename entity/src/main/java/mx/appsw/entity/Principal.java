package mx.appsw.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author kitto
 */
@Entity
public class Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_principal;

    @Basic
    private String rol;

    @Basic
    private String activo;

    @OneToMany
    private List<Permission> permissions;

    public Long getId_principal() {
        return id_principal;
    }

    public void setId_principal(Long id_principal) {
        this.id_principal = id_principal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<Permission> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        getPermissions().add(permission);
    }

    public void removePermission(Permission permission) {
        getPermissions().remove(permission);
    }

}
