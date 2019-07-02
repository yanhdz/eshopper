package com.mosis.entity.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "puesto")
public class Puesto {

    @Id
    @GeneratedValue
    @Column(name = "id_puesto", table = "puesto", nullable = false)
    private Integer idPuesto;

    @Basic
    @Column(name = "puesto", table = "puesto", length = 200)
    private String puesto;

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}