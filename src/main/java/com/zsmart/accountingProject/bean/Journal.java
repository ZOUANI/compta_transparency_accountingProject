package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @OneToMany(mappedBy = "journal")
    private List<OperationComptableGroupe> operationComptablesGroupe;
    private String libele;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public List<OperationComptableGroupe> getOperationComptablesGroupe() {
        return operationComptablesGroupe;
    }

    public void setOperationComptablesGroupe(List<OperationComptableGroupe> operationComptablesGroupe) {
        this.operationComptablesGroupe = operationComptablesGroupe;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }
}
