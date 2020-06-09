/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zsmart.accountingProject.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author MoulaYounes
 */
@Entity
public class OperationComptableGroupe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;

    @OneToMany(mappedBy = "operationComptableGroupe")
    private List<OperationComptable> operationComptables;
    private String code;
    @ManyToOne
    private Adherant adherant;
    @ManyToOne
    private Comptable comptable;
    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public Comptable getComptable() {
        return comptable;
    }

    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<OperationComptable> getOperationComptables() {
        return operationComptables;
    }

    public void setOperationComptables(List<OperationComptable> operationComptables) {
        this.operationComptables = operationComptables;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationComptableGroupe)) {
            return false;
        }
        OperationComptableGroupe other = (OperationComptableGroupe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zsmart.accountingProject.bean.OperationComptableGroupe[ id=" + id + " ]";
    }

}
