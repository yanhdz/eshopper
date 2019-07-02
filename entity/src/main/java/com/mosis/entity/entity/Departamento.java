package com.mosis.entity.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue
    @Column(name = "id_departamento", table = "departamento", nullable = false)
    private Integer idDepartamento;

    @Basic
    @Column(name = "departamento", table = "departamento", length = 100)
    private String departamento;

    @ManyToMany(mappedBy = "departamentoCollection")
    private List<CtoServicio> ctoServicioCollection;

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<CtoServicio> getCtoServicioCollection() {
        return ctoServicioCollection;
    }

    public void setCtoServicioCollection(List<CtoServicio> ctoServicioCollection) {
        this.ctoServicioCollection = ctoServicioCollection;
    }

}