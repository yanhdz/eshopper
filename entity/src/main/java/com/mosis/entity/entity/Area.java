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
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue
    @Column(name = "id_area", table = "area", nullable = false)
    private Integer idArea;

    @Basic
    @Column(name = "area", table = "area", length = 100)
    private String area;

    @ManyToMany
    private List<CtoServicio> ctoServicioCollection;

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<CtoServicio> getCtoServicioCollection() {
        return ctoServicioCollection;
    }

    public void setCtoServicioCollection(List<CtoServicio> ctoServicioCollection) {
        this.ctoServicioCollection = ctoServicioCollection;
    }

}