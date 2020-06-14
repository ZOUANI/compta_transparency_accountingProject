package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datedebut;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datefin;
    @OneToMany(mappedBy = "journal")
    private List<OperationComptable> operationComptables;
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

    public List<OperationComptable> getOperationComptables() {
        return operationComptables;
    }

    public void setOperationComptables(List<OperationComptable> operationComptables) {
        this.operationComptables = operationComptables;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }
}
