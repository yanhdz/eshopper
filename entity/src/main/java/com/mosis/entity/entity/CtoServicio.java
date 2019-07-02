package com.mosis.entity.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cto_servicio", uniqueConstraints = @UniqueConstraint(columnNames = "servicio"))
public class CtoServicio {

    @Id
    @GeneratedValue
    @Column(name = "id_cto_servicio", table = "cto_servicio", nullable = false)
    private Integer idCtoServicio;

    @Basic(optional = false)
    @Column(name = "clave", table = "cto_servicio", nullable = false, length = 245)
    private String clave;

    @Basic(optional = false)
    @Column(name = "servicio", table = "cto_servicio", nullable = false, length = 145)
    private String servicio;

    @Basic
    @Column(name = "id_cto_zona", table = "cto_servicio")
    private Integer idCtoZona;

    @Basic
    @Column(name = "time_zone", table = "cto_servicio", length = 200)
    private String timeZone;

    @OneToMany
    private List<Puesto> puestoes;

    @ManyToMany
    @JoinTable(name = "cto_servicio_departamento", joinColumns = @JoinColumn(name = "CTOSERVICIO_ID_CTO_SERVICIO", referencedColumnName = "ID_CTO_SERVICIO"), inverseJoinColumns = @JoinColumn(name = "DEPARTAMENTOES_ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"))
    private List<Departamento> departamentoCollection;

    @ManyToMany(mappedBy = "ctoServicioCollection")
    @JoinTable(name = "cto_servicio_area", joinColumns = @JoinColumn(name = "CTOSERVICIO_ID_CTO_SERVICIO", referencedColumnName = "ID_CTO_SERVICIO"), inverseJoinColumns = @JoinColumn(name = "AREAS_ID_AREA", referencedColumnName = "ID_AREA"))
    private List<Area> areaCollection;

    public Integer getIdCtoServicio() {
        return idCtoServicio;
    }

    public void setIdCtoServicio(Integer idCtoServicio) {
        this.idCtoServicio = idCtoServicio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Integer getIdCtoZona() {
        return idCtoZona;
    }

    public void setIdCtoZona(Integer idCtoZona) {
        this.idCtoZona = idCtoZona;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public List<Puesto> getPuestoes() {
        if (puestoes == null) {
            puestoes = new ArrayList<>();
        }
        return puestoes;
    }

    public void setPuestoes(List<Puesto> puestoes) {
        this.puestoes = puestoes;
    }

    public void addPuesto(Puesto puesto) {
        getPuestoes().add(puesto);
    }

    public void removePuesto(Puesto puesto) {
        getPuestoes().remove(puesto);
    }

    public List<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(List<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }

    public List<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(List<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

}